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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Base;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Hend
 */
public class ServiceReclamation {
     private ArrayList<Reclamation> reclamations;
    private static ServiceReclamation instance = null;
    private boolean resultOK;
    private ConnectionRequest req;

    public ServiceReclamation() {
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
    String url = Base.BASEE_URL + "post_reclamation?titre=" + titre + "&type=" + type + "&description=" + description + "&id_utilisateur=2"; 

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
            Object idObj = obj.get("idReclamation");
            if (idObj != null) {
                float idRec = Float.parseFloat(idObj.toString());
                t.setId_reclamation((int) idRec);
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
        String url = Base.BASEE_URL + "get_reclamation/";
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
    
    public ArrayList<Integer> getStatistics() {
    String url = Base.BASEE_URL + "statistics";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            try {
                ArrayList<Integer> stats = new ArrayList<>();
                JSONParser j = new JSONParser();
                Map<String, Object> reclamationsStatisticsJson = j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                System.out.println("reclamationsStatisticsJson: " + reclamationsStatisticsJson);
                List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationsStatisticsJson.get("root");
                System.out.println("list: " + list);
                for (Map<String, Object> obj : list) {
                    int nbTotalReclamations = Integer.parseInt(obj.get("nbTotalReclamations").toString());
                    int nbReclamationsNonTraitees = Integer.parseInt(obj.get("nbReclamationsNonTraitees").toString());
                    int nbReclamationsTraitees = Integer.parseInt(obj.get("nbReclamationsTraitees").toString());
                    int nbReclamationsEnAttente = Integer.parseInt(obj.get("nbReclamationsEnAttente").toString());

                    stats.add(nbTotalReclamations);
                    stats.add(nbReclamationsNonTraitees);
                    stats.add(nbReclamationsTraitees);
                    stats.add(nbReclamationsEnAttente);
                }
                req.removeResponseListener(this);
             
            } catch (Exception ex) {
                ex.printStackTrace();
                
            }
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return null;
}

   public boolean deleteReclamation(Reclamation r) {
   if (r == null || r.getId_reclamation() == 0) {
       System.out.println("Reclamation or ID is null");
   } else {
       System.out.println("Reclamation and ID are not null");
   }

   try {
       String url = Base.BASEE_URL + "delete_reclamation/" + r.getId_reclamation();
       ConnectionRequest req = new ConnectionRequest(url);
       req.setHttpMethod("DELETE");
       req.addResponseListener(evt -> {
           if (req.getResponseCode() == 200) {
               System.out.println("Reclamation deleted successfully");
           } else {
               System.out.println("Error deleting reclamation");
           }
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
       return true;
   } catch (Exception ex) {
       ex.printStackTrace();
       return false;
   }
}
   
   public Reclamation parsecat(String jsonText) {
    Reclamation c = new Reclamation();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> categorieJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        if (categorieJson.get("idReclamation") != null) {
            float id = Float.parseFloat(categorieJson.get("idReclamation").toString());
            c.setId_reclamation((int) id);
        }

        if (categorieJson.get("nomReclamation") != null) {
            c.setTitre(categorieJson.get("nomReclamation").toString());
        }

      
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return c;
}

   public void deleteVelo(int id_reclamation) {

        Dialog d = new Dialog();
        if (d.show("Delete Reclamation", "Do you really want to remove this Reclamation", "Yes", "No")) {

            req.setUrl(Base.BASEE_URL + "delete_reclamation/" + id_reclamation);

            NetworkManager.getInstance().addToQueueAndWait(req);
        d.dispose();
    }

   }

    }
  
