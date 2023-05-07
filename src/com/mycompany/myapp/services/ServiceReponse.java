/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.utils.Base;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hend
 */
public class ServiceReponse {
    private ArrayList<Reponse> reponses;
    private static ServiceReponse instance = null;
    private boolean resultOK;
    private ConnectionRequest req;

    private ServiceReponse() {
        req = new ConnectionRequest();
    }

    public static ServiceReponse getInstance() {
        if (instance == null) {
            instance = new ServiceReponse();
        }
        return instance;
    }

   public boolean addReponse(Reponse t) {
    String reponse = t.getReponse();
    String url = Base.BASE_URL + "post_reponse?reponse=" + reponse;
    url += "&id_reclamation=24"; 

    req.setUrl(url);
    req.setPost(false);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}


    public ArrayList<Reponse> parseReponses(String jsonText) {
    try {
        reponses = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> reponsesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) reponsesListJson.get("root");
        for (Map<String, Object> obj : list) {
            Reponse t = new Reponse();
            Object idObj = obj.get("id");
            if (idObj != null) {
                float id = Float.parseFloat(idObj.toString());
                t.setId_reponse((int) id);
            }
            t.setReponse(obj.get("reponse") != null ? obj.get("reponse").toString() : "null");
            reponses.add(t);
        }

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return reponses;
}
    


      public ArrayList<Reponse> getAllReponses() {
        String url = Base.BASE_URL + "get_reponse/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reponses = parseReponses(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reponses;
    }
    
   public boolean deleteReponse(int id_reponse) throws IOException {
    String url = Base.BASE_URL + "delete_reponse/";
    url += "2"; 
    req.setUrl(url);
    req.setPost(false);
    req.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(req);

    int responseCode = req.getResponseCode();
    if (responseCode == 200) {
        return true;
    } else {
        return false;
    }
}

public void delete_reponse(int id_reponse) {
    try {
        boolean success = deleteReponse(id_reponse);
        if (success) {
            Dialog.show("Success","Response deleted successfully",new Command("OK"));
        } else {
            Dialog.show("Error","Unable to delete response",new Command("OK"));
        }
    } catch (IOException ex) {
        Dialog.show("Error","Unable to delete response",new Command("OK"));
    }
}

    
    public ArrayList<Reponse> getReponse(int id_reclamation) {
    String url = Base.BASE_URL + "showFromRec_reponse/" + id_reclamation;
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            String response = new String(req.getResponseData());
            if (response.equals("Response deleted successfully.")) {
                // Handle the case where there is no response for the given reclamation
                reponses = new ArrayList<>();
            } else {
                reponses = parseReponses(response);
            }
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return reponses;
}

}