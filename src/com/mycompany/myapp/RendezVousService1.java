/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Annonce;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.entities.rendez_vous;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author ilyes
 */
public class RendezVousService1 {
    
    private ConnectionRequest req;
    private boolean resultOK;
    private static RendezVousService1 instance = null;
    private Resources res;
    private ArrayList<rendez_vous> rendez_vous;
    
    private RendezVousService1() {
        req = new ConnectionRequest();
    }
    
    public static RendezVousService1 getInstance() {
        if (instance == null) {
            instance = new RendezVousService1();
        }
        return instance;
    }
    
    public ArrayList<rendez_vous> parseCanidature(String jsonText) throws ParseException {
        try {
            rendez_vous = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) userListJson.get("root");
            for (Map<String, Object> obj : list) {
                //Création des Users et récupération de leurs données
                rendez_vous u = new rendez_vous();
                float id = Float.parseFloat(obj.get("idRendezVous").toString());
                u.setId_rendez_vous ((int) id); 

                String Date1 = (obj.get("DateRendezVous").toString());
                System.out.println("sss"+Date1);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
Date date = formatter.parse(Date1);
                System.out.println(date);
u.setDate_rendez_vous(date);
                u.setId_rendez_vous ((int) id);
                                Utilisateur u1 = new Utilisateur();

                String  nom = (obj.get("name").toString());
                u1.setUsername( nom);
                String  Heure = (obj.get("Heure").toString());
                u.setHeure_rendez_vous(Heure);
                                  
                int idUser = (int)Float.parseFloat(obj.get("id").toString());
                int idAnnonce = (int)Float.parseFloat(obj.get("idAnnonce").toString());

                u1.setId(idUser);
                Annonce an=new Annonce();
             an.setId_annonce(idAnnonce);
                System.out.println("qdqsdqsdsqd "+an.getId_annonce());
             u.setAnnonce(an);
                  u.setUser(u1);
                        rendez_vous.add(u);
                        System.out.println("fheezrzerezrrezr");
            }

        } catch (IOException ex) {

        }
        return rendez_vous;
    }

    public ArrayList<rendez_vous> getAllVelos() {
        ArrayList<rendez_vous> rendez_vousListe = new ArrayList<>();
        int id = 2;
        String url = "http://localhost:8000/rendez/vous/mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    rendez_vous = parseCanidature(new String(req.getResponseData()));
               
                req.removeResponseListener(this); 
                } catch (ParseException ex) {
                    System.out.println(ex);
        }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println("im gere");
        System.out.println("im gere" +rendez_vousListe.size());
        return rendez_vous;
    }

    public boolean addVelo(rendez_vous u) {
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("");
        // Convert the date to a string using the SimpleDateFormat object
        String dateString = dateFormat.format(u.getDate_rendez_vous());
        System.out.println("lb1"+u.getHeure_rendez_vous());
        System.out.println("lb2"+dateString);
        System.out.println("lb3"+u.getAnnonce().getId_annonce());
        System.out.println("lb4"+u.getUser().getId());
        System.out.println("lb1");
       // http://localhost:8000/rendez/vous/mobile/addRDV/28/5/10:11/05-05-2023
        String url = "http://localhost:8000/rendez/vous/mobile/addRDV/"+u.getUser().getId()+"/"+u.getAnnonce().getId_annonce()+"/"+u.getHeure_rendez_vous()+"/"+dateString;
        System.out.println("fsdsds "+url);
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
        return resultOK;
    }
    
    public boolean editVelo(rendez_vous u) {
SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("");
        
        // Convert the date to a string using the SimpleDateFormat object
        String dateString = dateFormat.format(u.getDate_rendez_vous());
        String url = "http://localhost:8000/rendez/vous/mobile/edit/"+u.getId_rendez_vous()+"/"+u.getUser().getId()+"/"+u.getAnnonce().getId_annonce()+"/"+u.getHeure_rendez_vous()+"/"+dateString;
        System.out.println(url);
            
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
        String url = "http://localhost:8000/rendez/vous/mobile/delete/"+idUser;
            System.out.println("url "+url);
            req.setUrl(url);
            //System.out.println(Statics.BASE_URL+"/deleteVeloMobile?id="+id);
            NetworkManager.getInstance().addToQueueAndWait(req);
        d.dispose();
    }
}
    
}
