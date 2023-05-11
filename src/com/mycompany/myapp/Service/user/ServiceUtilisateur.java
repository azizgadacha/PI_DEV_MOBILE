
package com.mycompany.myapp.Service.user;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.gui.HomeForm;
import com.mycompany.myapp.gui.User.ListUtilisateurs;
import com.mycompany.myapp.gui.User.SessionManager;
import com.mycompany.myapp.gui.User.SignInForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Chédy
 */
public class ServiceUtilisateur {
    
     private Resources theme;

       // public ArrayList<Utilisateur> utilisateurs;
    
    public static ServiceUtilisateur instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceUtilisateur() {
         req = new ConnectionRequest();
    }

    public static ServiceUtilisateur getInstance() {
        if (instance == null) {
            instance = new ServiceUtilisateur();
        }
        return instance;
    }
    
    
    public  void login(TextField username,TextField password,Resources res) {
        //
        String  url = "http://localhost:8000/logi?username="+username.getText().toString()+"&password="+password.getText().toString()
      //  String  url = "http://localhost:8000//login?username="+username.getText().toString()+"&password="+password.getText().toString()
                   ;
                req = new ConnectionRequest(url,false);
               req.setUrl(url);
              //  req.addArgument("username",username.getText());
                //req.addArgument("password",password.getText());
                req.addResponseListener((action) -> {
                     try {
            
                            JSONParser j = new JSONParser();
                            String json = new String(req.getResponseData()) ;
                            if (json.equals("failed")){

                                Dialog.show("Echec d'authenfication", "username ou mot de passe éronné", "Ok", null);
                             
                            }
                            else{

                            System.out.println("hello ="+ json);

                               Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                            System.out.println(user);
                              

            List<Map<String, Object>> list = (List<Map<String, Object>>) user.get("root");
            for (Map<String, Object> obj : list) {
                            
                            
                            float id = Float.parseFloat(obj.get("tid").toString());
                                System.out.println((int)id);
                                SessionManager.setId((int)id);
                                SessionManager.setPass(password.getText());
                                SessionManager.setUserName(obj.get("name").toString());
                                SessionManager.setEmail(obj.get("email").toString());
                                
                                if (user.get("photo") !=null){
                                SessionManager.setPhoto(obj.get("photo").toString());
                                }
                            
                                if(SessionManager.getUserName().equals("aziz.gadacha@esprit.tn")&& SessionManager.getPass().equals("testtest")) {
                                    new ListUtilisateurs(res).show();
                                }
                                else if(user.size()>0 ) {
                                           

        new HomeForm(res).show();
                            }}
                            
                            }
                            
                     }
                   catch(Exception e ){
                       e.printStackTrace();
                   }
                                
                });
                              NetworkManager.getInstance().addToQueue(req);
          
    }
    
    
    
    
    
    
      public ArrayList<Utilisateur> getAllUsers() 
     {
        ArrayList<Utilisateur> listUsers = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
                con.setUrl("http://localhost:8000/alluser");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;                
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapCpmments = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapCpmments.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Utilisateur user = new Utilisateur();
                        float id = Float.parseFloat(obj.get("id").toString());
                    //    float idAuteur = Float.parseFloat(obj.get("id_auteur").toString());
                        String name = obj.get("name").toString();      
                        String contact = obj.get("contact").toString();

                        String email=obj.get("email").toString();

                        ArrayList<String> rolesList=new ArrayList<>();
                        rolesList.add(obj.get("roles").toString());
                           String image = obj.get("photo").toString();
                        user.setId((int) id);
                      user.setUsername(name);
                        user.setEmail(email);                        
                        user.setContact(contact);

                        
                        listUsers.add(user);

                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                } 

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUsers;
    }
     
      
      
   
     
     public String getUsername(String us) {
        ArrayList<String> listTopics = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/login?username="+us);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> topics = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(topics);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) topics.get("root");
                    for (Map<String, Object> obj : list) {
                        
                        listTopics.add(obj.get("username").toString());
                       

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTopics.get(0);
    }
     
  public static Image UrlImage(String photo){
        String url ="http://localhost/ProjetWebSymfony/test/web/app_dev.php/api/images_user?img="+photo;
                EncodedImage placeholder = EncodedImage.createFromImage(Resources.getGlobalResources().getImage("photo-profile.jpg"), false);
                Image urli = URLImage.createToStorage(placeholder,"Medium_"+url, url,URLImage.RESIZE_SCALE);
                
        return urli;        
    }
     
     
        
    
    
    
    
    
    
    
    
    
    public  void EditUser(String username, String email, String contact){
        
    String url = "http://localhost:8000/edituser";
                MultipartRequest req = new MultipartRequest();
                
                req.setUrl(url);
                req.setPost(true);
                req.addArgument("id", String.valueOf(SessionManager.getId()));
                req.addArgument("name", username);
                req.addArgument("contact", contact);
                req.addArgument("email", email);
                System.out.println(email);
                req.addResponseListener((response)-> {
                    
                    byte[] data = (byte[]) response.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                    } else {
                        Dialog.show("Erreur", "Echec de modification", "Ok", null);
                    }                    
                });
                NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
   
        public static boolean resultOk = true;

        
      
    //Delete 
    public boolean deleteUser(int id ) {
        String url = "http://localhost:8000/delete?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
  
    
}
    
    
      