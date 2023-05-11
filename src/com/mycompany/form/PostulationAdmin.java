/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.form;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.itextpdf.text.DocumentException;
import com.mycompany.entity.Annoce;
import com.mycompany.entity.Postulation;
import com.mycompany.service.FileService;
import com.mycompany.service.PdfService;
import com.mycompany.service.PostulationService;
import java.io.IOException;


public class PostulationAdmin extends Form{
    
    
    
           Resources theme;

     public PostulationAdmin()  {
        super("Mes Postulations", BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        this.getToolbar();
        Label logi = new Label("Mes Postulations");

    

              for(Postulation c: new PostulationService().getAllPostulation()){
           
 
                    try {    
                        this.add(addItem(c));
                    } catch (IOException ex) {
                    }
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Postulation e) throws IOException{
          
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        
        Label libelle_titre = new Label("titre");

        Label libelle_societe = new Label("societe");
        
         Label etat = new Label("Etat");
        String titres = "";
        String nom="";
        
            for (Annoce a : new FileService().getAllAnnonce()) {
                
                if (a.getId() == e.getId_annonce()){
                
                    titres =titres+a.getNomSociete();
                    nom= nom+a.getNomSociete();
                
                }
                
            }
          Button btn=new Button("Details");

        cn2.add(libelle_titre).add(titres);
        cn2.add(libelle_societe).add(nom);
        cn2.add(etat).add(e.getEtat());
         cn2.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      

Button Fact = new Button("Postulation");
       Fact.addActionListener((evv)-> {
           
           if (Dialog.show("Confirmation", "Voulez-vous obtenir detail de Postulation en forme pdf  ?", "Oui", "Non")){
       
               try {
                  new PdfService().recupererpdf(e);
               } catch (DocumentException ex) {
            //       Logger.getLogger(AfficherLivraison.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IOException ex) {
             //      Logger.getLogger(AfficherLivraison.class.getName()).log(Level.SEVERE, null, ex);
               }
               Dialog.show("PDF "
                       , "Enregistré avec succés", "", "OK");
            
      }
       });
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());

       Button Supprimer = new Button("Accpter");
       Button modifier = new Button("Refuser");

       if (!e.getEtat().equals("en cours")){
       
           Supprimer.setVisible(false);
           modifier.setVisible(false);
       }

     
       Supprimer.addActionListener(sup ->  
       
       {
       new PostulationService().AccepterPost(e.getId());
       new PostulationAdmin().show();
       }
          
       );
           
       modifier.addActionListener(sup ->  
       
       {
       new PostulationService().reffuserUser(e.getId());
       new PostulationAdmin().show();
       }
          
       );
         
             f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
         Label libelle_titre1 = new Label("titre");

        Label libelle_societe2 = new Label("societe");
        
         Label etat1 = new Label("Etat");
        String titres1 = "";
        String nom1="";
        
            for (Annoce a : new FileService().getAllAnnonce()) {
                
                if (a.getId() == e.getId_annonce()){
                
                    titres1 =titres1+a.getNomSociete();
                    nom1= nom1+a.getNomSociete();
                
                }
                
            }
      
            f2.add(libelle_titre1).add(titres1).add(libelle_societe2).add(nom1).add(etat1).add(e.getEtat()).add(Supprimer).add(modifier).add(Fact);
            f2.show();
         
        });
        return cn1;
                
    
}
    
    
    
}
