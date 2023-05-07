/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
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
    String url = Statics.BASE_URL1 + "post_reponse?reponse=" + reponse;
    url += "&id_reclamation=7"; 

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
        String url = Statics.BASE_URL1 + "get_reponse/";
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
    String url = Statics.BASE_URL1 + "delete_reponse/";
    url += "3"; 
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

private Reponse parseReponse(String response) {
    try {
        JSONParser parser = new JSONParser();
        Map<String, Object> json = parser.parseJSON(new CharArrayReader(response.toCharArray()));
        //int id = (int) json.get("id");
        String reponse = (String) json.get("reponse");
        //Date date = new Date(json.getLong("date"));
        return new Reponse( reponse);
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
        return null;
    }
}


   

   public Reponse getReponse(int id_reclamation) {
    String url = Statics.BASE_URL1 + "showFromRec_reponse/";
    url += "1";
    req.setUrl(url);
    req.setPost(false);
    Reponse reponse = null;
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            Reponse reponse;
            String response = new String(req.getResponseData());
            if (response.equals("No Response For This Reclamation.")) {
                // Handle the case where there is no response for the given reclamation
                reponse = null;
            } else {
                reponse = parseReponse(response);
            }
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return reponse;
}


}