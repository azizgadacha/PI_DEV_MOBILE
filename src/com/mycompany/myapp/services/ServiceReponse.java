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
import com.codename1.ui.Label;
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
    url += "&id_reclamation=23"; 

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
            Object idObj = obj.get("idReponse");
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
    url += "47"; 
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
    //try {
        //boolean success = deleteReponse(id_reponse);
        //if (success) {
           // Dialog.show("Success","Response deleted successfully",new Command("OK"));
        //} else {
            //Dialog.show("Error","Unable to delete response",new Command("OK"));
        }
    //} catch (IOException ex) {
       // Dialog.show("Error","Unable to delete response",new Command("OK"));
    //}
 
 public Reponse parsecat(String jsonText) {
    Reponse c = new Reponse();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> reponseJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        if (reponseJson.get("idReponse") != null) {
            float id = Float.parseFloat(reponseJson.get("idReponse").toString());
            c.setId_reponse((int) id);
        }

        if (reponseJson.get("nomReponse") != null) {
            c.setReponse(reponseJson.get("nomReponse").toString());
        }

      
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return c;
}

   public void deleteVelo(int id_reponse) {

        Dialog d = new Dialog();
        if (d.show("Delete Reponse", "Do you really want to remove this Reponse", "Yes", "No")) {

            req.setUrl(Base.BASE_URL + "delete_reponse/" + id_reponse);

            NetworkManager.getInstance().addToQueueAndWait(req);
        d.dispose();
    }
   }
private ArrayList<Reponse> parseReponse(String jsonText) {
    ArrayList<Reponse> reponses = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Object obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        if (obj instanceof Map) {
            Map<String, Object> reponsesListJson = (Map<String, Object>) obj;
            if (reponsesListJson.containsKey("root")) {
                Object rootObj = reponsesListJson.get("root");
                if (rootObj instanceof List) {
                    List<Map<String, Object>> list = (List<Map<String, Object>>) rootObj;
                    for (Map<String, Object> r : list) {
                        Reponse reponse = new Reponse();
                        Object idObj = r.get("idReponse");
                        if (idObj != null) {
                            int id = Integer.parseInt(idObj.toString());
                            reponse.setId_reponse(id);
                        }
                        reponse.setReponse(r.get("reponse") != null ? r.get("reponse").toString() : null);
                        Object mediaObj = r.get("media");
                        if (mediaObj instanceof Map) {
                            Map<String, Object> mediaMap = (Map<String, Object>) mediaObj;
                            String url = (String) mediaMap.get("url");
                            String type = (String) mediaMap.get("type");
                            
                        }
                        reponses.add(reponse);
                    }
                } else {
                    System.out.println("Error parsing JSON string: root is not a list");
                }
            } else {
                System.out.println("Error parsing JSON string: root key not found");
            }
        } else {
            System.out.println("Error parsing JSON string: not a map");
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    } catch (NumberFormatException ex) {
        System.out.println("Error parsing integer: " + ex.getMessage());
    } catch (ClassCastException ex) {
        System.out.println("Error casting object: " + ex.getMessage());
    }
    return reponses;
}



public Reponse getReponse(int id_reclamation) {
    String url = Base.BASE_URL + "showFromRec_reponse/" + id_reclamation;
    req.setUrl(url);
    req.setPost(false);
    final Reponse[] reponse = {null};
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            reponses = parseReponse(new String(req.getResponseData()));
            if (!reponses.isEmpty()) {
                reponse[0] = reponses.get(0);
            }
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return reponse[0];
}


}


