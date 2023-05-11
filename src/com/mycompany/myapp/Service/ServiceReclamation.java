/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author acer
 */
public class ServiceReclamation {
  private ArrayList<Reclamation> reclamations;
    private static ServiceReclamation instance = null;
    private boolean resultOK;
    private ConnectionRequest req;

    private ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }
    
    public boolean addReclamation(Reclamation t) {
    String titre = t.getTitre();
    String type = t.getType();
    String description = t.getDescription();
    String url = Statics.BASEE_URL + "post_reclamation?titre=" + titre + "&type=" + type + "&description=" + description + "&id=18"; 

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

    
   public ArrayList<Reclamation> parseReclamations(String jsonText) {
    try {
        reclamations = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> reclamationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationsListJson.get("root");
        for (Map<String, Object> obj : list) {
            Reclamation t = new Reclamation();
            Object idObj = obj.get("id_reclamation");
            if (idObj != null) {
                int id = Integer.parseInt(idObj.toString());
                t.setId_reclamation(id);
            }
            t.setTitre(obj.get("titre") != null ? obj.get("titre").toString() : "null");
            t.setType(obj.get("type") != null ? obj.get("type").toString() : "null");
            t.setDescription(obj.get("description") != null ? obj.get("description").toString() : "null");
            //t.setDate(obj.get("date") != null ? (Date)obj.get("date") : null);
            //t.setStatut(obj.get("statut") != null ? obj.get("statut").toString() : null);
            reclamations.add(t);
        }
    } catch (IOException ex) {
        // handle exception
    }

    return reclamations;
}



    public ArrayList<Reclamation> getAllReclamations() {
        String url = Statics.BASEE_URL + "get_reclamation/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamations = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
}