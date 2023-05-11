/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.form;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.File;
import com.mycompany.service.FileService;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class FileForm extends Form{
    
     String fileCv ;
      String fileDep ;
       String fileMoti ;
    
      Resources theme;
         FileService ess = new FileService();
    
     public FileForm()  {
        super("Files", BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        this.getToolbar();
        Label logi = new Label("Mes File");

    
         this.getToolbar();
        this.getToolbar().addCommandToOverflowMenu("Add File", null, ev->{
        Form addEvent = new Form("Add File",BoxLayout.y());
            Label AJOUT = new Label("ADD File");
            addEvent.add(AJOUT);
;

        Button save = new Button("Ajouter");
        Button cv = new Button("Upload Cv");
        Button motivation = new Button("Upload Lettre Motivation");
        Button deplome = new Button("Upload Deplome");
        
        
        addEvent.add(cv);
        addEvent.add(motivation);
        addEvent.add(deplome);
        addEvent.add(save);
        
        
         cv.addActionListener(new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://127.0.0.1/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    fileCv=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                                        
            }
        });
         
         
         
         
         
         deplome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://127.0.0.1/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    fileDep=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                                        
            }
        });
         
         
         
         
         motivation.addActionListener(new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://127.0.0.1/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    fileMoti=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                                        
            }
        });

        save.addActionListener(l
                                -> {

                            if (fileCv.equals("")) {
                                Dialog.show("Erreur", "Champ vide de firstname ", "OK", null);

                            }  else if (fileDep.equals("")) {
                                Dialog.show("Erreur", "Champ vide de lastname ", "OK", null);

                            }
                          else if (fileMoti.equals("")) {
                                Dialog.show("Erreur", "Champ vide de lastname ", "OK", null);

                            }
                            else {
                           
                                File e = new File();
                                e.setCv(fileCv);
                                e.setDeplome(fileDep);
                                e.setLettreM(fileMoti);
                                e.setId_user(30);
                                System.out.println("forms.addEvet.addItem()"+e);
                                if (ess.addFile(e) == true) {
                                    Dialog.show("Ajouter File", "Ajouter File aves success ", "OK", null);
                                    new FileForm().show();
                                    
                                    
                                } else {
                                    Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
                                }

                            }

                        }
                        );
 addEvent.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
        
        addEvent.show();
 });
              for(File c: ess.getAllFile()){
           
 
                    try {    
                        this.add(addItem(c));
                    } catch (IOException ex) {
                    }
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(File e) throws IOException{
          
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        
        Label libelle_sujet = new Label("Cv");
        String image = new String("http://127.0.0.1/PidevSymfony/public/images/");
        EncodedImage enc = EncodedImage.create("/sepf.png");
        Image cvim = URLImage.createToStorage(enc, "local"+e.getCv() , image+e.getDeplome());
       
        Label libelle_contenue = new Label("Deplome");
        Image depim = URLImage.createToStorage(enc, "local"+e.getCv() , image+e.getDeplome());

        Label libelle_motivation = new Label("LettreMotivation");
        Image motim = URLImage.createToStorage(enc, "local"+e.getCv() , image+e.getDeplome());


     

        Button btn=new Button("Details");

        cn2.add(libelle_sujet).add(cvim);
        
        cn2.add(libelle_contenue).add(depim);
        cn2.add(libelle_motivation).add(motim);
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());

       Button Supprimer = new Button("Supprimer");


     
       Supprimer.addActionListener(sup ->  
       
       {
           
           
            if (ess.DeleteConsultation(e.getId())) {
                                        Dialog.show("Supprimer File", "File Supprimer aves success ", "OK", null);
                                        
                                        new FileForm().show();
                                    } else {
                                        Dialog.show("Erreur", " Erreur de suppression ", "OK", null);
                                    }
           
           
           
         
       
       
       }
       
       );
         
             f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
               Label lib_titre = new Label("Cv");
      
              Label lib_Lieu = new Label("Deplome");
   
              Label lib_Description = new Label("Motivation");
 

        Image cvimdet = URLImage.createToStorage(enc, "local"+e.getCv() , image+e.getDeplome());

        Image depimdet = URLImage.createToStorage(enc, "local"+e.getCv() , image+e.getDeplome());
        
        Image moimdet = URLImage.createToStorage(enc, "local"+e.getCv() , image+e.getDeplome());

 
       
      
            f2.add(lib_titre).add(cvimdet).add(lib_Lieu).add(depimdet).add(lib_Description).add(moimdet).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    
}
        
}
