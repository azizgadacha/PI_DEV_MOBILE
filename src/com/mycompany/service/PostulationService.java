/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.Format;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entity.File;
import com.mycompany.entity.Postulation;
import com.mycompany.utils.DataSource;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class PostulationService {
    
    
    
     private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Postulation> comments;

    public PostulationService() {
        request = DataSource.getInstance().getRequest();

    }
    
    
    public boolean addPostulation(Postulation r) {

        String url = Statics.BASE_URL + "/api/postulation/addpostulation?user=" + r.getIdUser() + "&annonce=" + r.getId_annonce();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    
    
    public ArrayList<Postulation> getAllPostulation() {
        String url = Statics.BASE_URL + "/api/postulation";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    comments = parseType(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return comments;
    }
    
    
    
    
    public ArrayList<Postulation> parseType(String jsonText) throws ParseException {
        try {
            comments = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                ;
                int id = (int) Float.parseFloat(obj.get("id").toString());

                String etat = obj.get("etat").toString();
                Map map = ((Map) obj.get("annoncePostulation")) ;
                Double idannoce = (Double) map.get("idAnnonce");
                
                Map map1 = ((Map) obj.get("userPostulation")) ;
                Double iduser = (Double) map1.get("id");



                Postulation v = new Postulation();
                v.setId(id);
                v.setEtat(etat);
                v.setId_annonce(idannoce.intValue());
                v.setIdUser(iduser.intValue());

                 comments.add(v);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return comments;
    }
    
    
    
        public boolean DeleteConsultation(int id) {

        String url = Statics.BASE_URL + "/api/supprimerfile/" + id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
        
        
       public boolean ModifierConsultation(File r) {

        String url = Statics.BASE_URL + "/api/modifierConsultation?cv=" + r.getCv() + "&deplome=" + r.getDeplome() + "&motivation=" + r.getLettreM();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
       
       
       
             public boolean AccepterPost(int id) 
      {
            String url = Statics.BASE_URL + "/api/accepter/" + id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;  
      }

      
       public boolean reffuserUser(int id) 
      {
            String url = Statics.BASE_URL + "/api/refuser/" + id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;  
      }

            
    
}
