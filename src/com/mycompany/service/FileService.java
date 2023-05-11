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
import com.mycompany.entity.Annoce;
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
public class FileService {
    
    
    private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<File> comments;
    public ArrayList<Annoce> annonce;
    public FileService() {
        request = DataSource.getInstance().getRequest();

    }
    
    
    public boolean addFile(File r) {

        String url = Statics.BASE_URL + "/api/file/addfile?cv=" + r.getCv() + "&deplome=" + r.getDeplome() + "&motivation=" + r.getLettreM()+"&user="+r.getId_user();
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

    
    
    public ArrayList<File> getAllFile() {
        String url = Statics.BASE_URL + "/api/file/all";

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
    
    
    
    
    public ArrayList<File> parseType(String jsonText) throws ParseException {
        try {
            comments = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                ;
                int id = (int) Float.parseFloat(obj.get("idFile").toString());

                String cv = obj.get("namecv").toString();
                String motivation = obj.get("namemotivation").toString();
                String Deplome = obj.get("namedeplome").toString();
//                int phone = (int) Float.parseFloat(obj.get("phone").toString());
               
             //   Map map = ((Map) obj.get("userFile")) ;
             //   Double idUser = (Double) map.get("id");
      

                File v = new File();
               
                v.setId(id);
                v.setDeplome(Deplome);
                v.setCv(cv);
                v.setLettreM(Deplome);
              //  v.setId_user(idUser.intValue());
               // v.setDure(datedebut);  
               // v.setVeterinaire(veterinaire);

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

        String url = Statics.BASE_URL + "/api/modifierfile?cv=" + r.getCv() + "&deplome=" + r.getDeplome() + "&motivation=" + r.getLettreM()+"&id="+r.getId();
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
     



    public ArrayList<Annoce> getAllAnnonce() {
        String url = Statics.BASE_URL + "/api/annonce/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    annonce = parseTypeAnnoce(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return annonce;
    }
    
    
    
    
    public ArrayList<Annoce> parseTypeAnnoce(String jsonText) throws ParseException {
        try {
            annonce = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                ;
                int id = (int) Float.parseFloat(obj.get("idAnnonce").toString());

                String titre = obj.get("titre").toString();
                String societe = obj.get("nomSociete").toString();
//                int phone = (int) Float.parseFloat(obj.get("phone").toString());
               
             //   Map map = ((Map) obj.get("userFile")) ;
             //   Double idUser = (Double) map.get("id");
      

                Annoce v = new Annoce();
               
                v.setId(id);
                v.setNomSociete(titre);
                v.setTitre(societe);
              //  v.setId_user(idUser.intValue());
               // v.setDure(datedebut);  
               // v.setVeterinaire(veterinaire);


                annonce.add(v);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return annonce;
    }       
    
}
