/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;



import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.entities.Annonce;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.entities.candidature;
/**
 *
 * @author ilyes
 */
public class CandidatureService {
    
    private ConnectionRequest req;
    private boolean resultOK;
    private static CandidatureService instance = null;
    private Resources res;
    private ArrayList<candidature> candidatures;
    
    private CandidatureService() {
        req = new ConnectionRequest();
    }
    
    public static CandidatureService getInstance() {
        if (instance == null) {
            instance = new CandidatureService();
        }
        return instance;
    }
    
    public ArrayList<candidature> parseCanidature(String jsonText) {
        try {
            candidatures = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) userListJson.get("root");
            for (Map<String, Object> obj : list) {
                //Création des Users et récupération de leurs données
                candidature u = new candidature();
                float id = Float.parseFloat(obj.get("idCandidature").toString());
                u.setId_candidature((int) id);
                float note = Float.parseFloat(obj.get("note").toString());
                u.setNote(note);
                Utilisateur u1 = new Utilisateur();
                String name = (obj.get("name").toString());
                u1.setUsername(name);                    
                int idUser = (int)Float.parseFloat(obj.get("id").toString());
                int idAnnonce = (int)Float.parseFloat(obj.get("idAnnonce").toString());
               Annonce an=new Annonce();
               an.setId_annonce(idAnnonce);
                u1.setId(idUser);
                u.setAnnonce(an);
             
                                                            
                u.setUtilisateur(u1);
                        candidatures.add(u);

            }

        } catch (IOException ex) {

        }
        return candidatures;
    }

    public ArrayList<candidature> getAllVelos() {
        ArrayList<candidature> listUser = new ArrayList<>();
        int id = 2;
        String url = "http://localhost:8000/candidature/mobile/5";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                candidatures = parseCanidature(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return candidatures;
    }

    public boolean addVelo(candidature u) {

        String url = "http://localhost:8000/candidature/mobile/5";
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean editVelo(candidature u) {

        String url = "http://localhost:8000/candidature/mobile/5";
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); 

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public void deleteVelo(float id) {
        System.out.println("rrrrrrrrrrrr"+id);
        int idUser=(int)id;
        Dialog d = new Dialog();
        if (d.show("Refuser", "Est ce que vous voulez vraiment refuser la candidature", "Yes", "No")) {
            System.out.println("dzaezez " );
        String url = "http://localhost:8000/candidature/mobile/delete/"+idUser;

            req.setUrl(url);
            //System.out.println(Statics.BASE_URL+"/deleteVeloMobile?id="+id);
            NetworkManager.getInstance().addToQueueAndWait(req);
        d.dispose();
    }
}
    
}
