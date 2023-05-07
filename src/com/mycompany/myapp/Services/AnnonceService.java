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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Annonce;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author acer
 */
public class AnnonceService {  
        private ConnectionRequest req;
    private boolean resultOK;
    private static AnnonceService instance = null;
    private Resources res;
    private ArrayList<Annonce> annonce;
    
    private AnnonceService() {
        req = new ConnectionRequest();
    }
    
    public static AnnonceService getInstance() {
        if (instance == null) {
            instance = new AnnonceService();
        }
        return instance;
    }
@SuppressWarnings("unchecked")
 
    public ArrayList<Annonce> parseAnnonce(String jsonText) throws ParseException {
        try {
          annonce = new ArrayList<>();
        JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

        Map<String, Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) userListJson.get("root");
        for (Map<String, Object> obj : list) {
                //Création des Users et récupération de leurs données
                Annonce u = new Annonce();
                float id = Float.parseFloat(obj.get("idannonce").toString());
                u.setId_annonce((int) id); 
                
                String Date1 = (obj.get("DateDebut").toString());
                System.out.println("sss"+Date1);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = formatter.parse(Date1);
                System.out.println(date);
                u.setDateDebut(date);
                String Date2 = (obj.get("DateFin").toString());
                System.out.println("sss"+Date2);
                SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
                Date date2 = formatter.parse(Date2);
                System.out.println(date);
                u.setDateDebut(date);
              String titre = obj.get("Titre").toString();
                u.setTitre(titre);
                String Description = obj.get("Description").toString();
                u.setTitre(titre);
                String TypeContrat = obj.get("TypeContrat").toString();
                u.setTitre(titre);
                        annonce.add(u);
                        
                        System.out.println("fheezrzerezrrezr");
            }

        } catch (IOException ex) {

        }
        return annonce;
    }


    public ArrayList<Annonce> getAllannonces() {
        ArrayList<Annonce> listannonce = new ArrayList<>();
        int id = 2;
        String url = Statics.BASE_URL + "/categorie/allcategories";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    annonce = parseAnnonce(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return annonce;
    }
    


//    public boolean addVelo(Annonce a) {
//         
//
//        String url = Statics.BASE_URL +"/annonce/new-json?nomCategorie=" +c.getNom_categorie(); //création de l'URL
//        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        System.out.println(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
//    
//    public boolean editVelo(categorie c) {
//
//        String url = Statics.BASE_URL + "/api/edit?id="+ u.getId()+ "&email=" + u.getEmail()+ "&password=" + u.getPassword()+"&username=" + u.getUsername()+ "&age=" + u.getAge()+ "&address=" + u.getAddress(); //création de l'URL
//        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        System.out.println(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this); 
//
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }

//    public void deleteVelo(int id_annonce) {
//
//        Dialog d = new Dialog();
//        if (d.show("Delete Velo", "Do you really want to remove this Categorie", "Yes", "No")) {
//
//            req.setUrl(Statics.BASE_URL + "/a/delete_json? id_categorie=" + id_categorie);
//
//            NetworkManager.getInstance().addToQueueAndWait(req);
//        d.dispose();
//    }
//}
}
