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
import com.codename1.l10n.ParseException;
import com.codename1.ui.Dialog;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.categorie;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;



/**
 *
 * @author acer
 */
public class CategorieService {
     private ConnectionRequest req;
    private boolean resultOK;
    private static CategorieService instance = null;
    private Resources res;
    private ArrayList<categorie> categorie;
    
    private CategorieService() {
        req = new ConnectionRequest();
    }
    
    public static CategorieService getInstance() {
        if (instance == null) {
            instance = new CategorieService();
        }
        return instance;
    }
@SuppressWarnings("unchecked")
 
    public ArrayList<categorie> parsecategorie(String jsonText) throws ParseException {
        try {
          categorie = new ArrayList<>();
        JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

        Map<String, Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) userListJson.get("root");
        for (Map<String, Object> obj : list) {
                //Création des Users et récupération de leurs données
                categorie u = new categorie();
                float id = Float.parseFloat(obj.get("idcategorie").toString());
                u.setId_categorie((int) id); 
              String nom = obj.get("nomCategorie").toString();
                u.setNom_categorie(nom);
                        categorie.add(u);
                        System.out.println("fheezrzerezrrezr");
            }

        } catch (IOException ex) {

        }
        return categorie;
    }
    
    


    public ArrayList<categorie> getAllcategories() {
        ArrayList<categorie> listcategorie = new ArrayList<>();
        int id = 2;
        String url = Statics.BASE_URL + "/categorie/allcategories";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    categorie = parsecategorie(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categorie;
    }
    


    public boolean addVelo(categorie c) {
         

        String url = Statics.BASE_URL +"/categorie/new-json?nomCategorie=" +c.getNom_categorie(); //création de l'URL
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
    
    
public categorie parsecat(String jsonText) {
    categorie c = new categorie();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> categorieJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        if (categorieJson.get("idCategorie") != null) {
            float id = Float.parseFloat(categorieJson.get("idCategorie").toString());
            c.setId_categorie((int) id);
        }

        if (categorieJson.get("nomCategorie") != null) {
            c.setNom_categorie(categorieJson.get("nomCategorie").toString());
        }

      
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return c;
}
    
     public boolean UpdateCategorie(categorie c){
         int id_categorie = c.getId_categorie();
         String nom_categorie = c.getNom_categorie();
          String url = Statics.Update_Categorie +id_categorie +"?nomCategorie=" + nom_categorie;
          ConnectionRequest req = new ConnectionRequest();
          req.setUrl(url);
          req.setPost(true);
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


    public void deleteVelo(int id_categorie) {

        Dialog d = new Dialog();
        if (d.show("Delete categorie", "Do you really want to remove this Categorie", "Yes", "No")) {

            req.setUrl(Statics.BASE_URL + "/categorie/delete_json/" + id_categorie);

            NetworkManager.getInstance().addToQueueAndWait(req);
        d.dispose();
    }
}
  public categorie getCategoryById(int id_categorie) {
    String url = Statics.Show_Categorie + id_categorie;
    req.setUrl(url);
    req.setPost(false);
    final categorie[] categorie = {null}; 
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            categorie[0] = parsecat(new String(req.getResponseData())); 
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return categorie[0]; 
}  
    
//
//  public categorie getCategoryById(int id_categorie) {
//    categorie cat = null;
//    String url = Statics.BASE_URL + "/categorie/getCategoryById?id_categorie=" + id_categorie;
//    req.setUrl(url);
//    req.setPost(false);
//    req.addResponseListener(new ActionListener<NetworkEvent>() {
//        @Override
//        public void actionPerformed(NetworkEvent evt) {
//            try {
//                JSONParser jsonParser = new JSONParser();
//                Map<String, Object> response = jsonParser.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                if (response.containsKey("root")) {
//                    ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) response.get("root");
//                    if (!list.isEmpty()) {
//                        Map<String, Object> obj = list.get(0);
//                        categorie = new categorie();
//                        float id = Float.parseFloat(obj.get("idcategorie").toString());
//                        cat.setId_categorie((int) id);
//                        String nom = obj.get("nomCategorie").toString();
//                        cat.setNom_categorie(nom);
//                    }
//                }
//            } catch (IOException | ParseException ex) {
//                System.out.println(ex);
//            }
//            req.removeResponseListener(this);
//        }
//    });
//    NetworkManager.getInstance().addToQueueAndWait(req);
//    return cat;
//}

    
}
