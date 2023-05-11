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
import com.mycompany.entity.Annoce;
import com.mycompany.entity.File;
import com.mycompany.entity.Postulation;
import com.mycompany.service.FileService;
import com.mycompany.service.PostulationService;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class AnnoceForm extends Form{
    
     String fileCv ;
      String fileDep ;
       String fileMoti ;
    
      Resources theme;
         FileService ess = new FileService();
    
     public AnnoceForm()  {
        super("Annoces", BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        this.getToolbar();
        Label logi = new Label("Mes Annoces");

    

              for(Annoce c: ess.getAllAnnonce()){
           
 
                    try {    
                        this.add(addItem(c));
                    } catch (IOException ex) {
                    }
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Annoce e) throws IOException{
          
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        
        Label libelle_titre = new Label("titre");

        Label libelle_societe = new Label("societe");

        Label societe = new Label(e.getTitre());
        Label libellnom = new Label(e.getNomSociete());
        
        Button btn=new Button("Details");

        cn2.add(libelle_titre).add(societe);
        
        cn2.add(libelle_societe).add(libellnom);
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());

       Button Supprimer = new Button("Postuler");


     
       Supprimer.addActionListener(sup ->  
       
       {
           Postulation p = new Postulation();
           p.setId_annonce(e.getId());
           p.setIdUser(6);
           
            if (new PostulationService().addPostulation(p)) {
                                        Dialog.show("Postuler ", "Postuler avec success ", "OK", null);
                                        
                                        new MesPostulationForm().show();
                                    } else {
                                        Dialog.show("Erreur", " Erreur de Postulation ", "OK", null);
                                    }
           
           
           
         
       
       
       }
       
       );
         
             f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
               Label lib_titre = new Label("Titre");
      
              Label c = new Label("Nom Societe");
   
 

      
            f2.add(lib_titre).add(e.getTitre()).add(c).add(e.getNomSociete()).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    
}
        
}
