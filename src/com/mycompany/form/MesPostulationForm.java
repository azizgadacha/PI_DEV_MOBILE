/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.form;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entity.Annoce;
import com.mycompany.entity.Postulation;
import com.mycompany.service.FileService;
import com.mycompany.service.PostulationService;
import java.io.IOException;


public class MesPostulationForm extends Form{
    
           Resources theme;

     public MesPostulationForm()  {
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
        Container photos = new Container(BoxLayout.y());
        try {
    Image lineImage = Image.createImage("/sepf.png"); // replace with your line image filename
    ScaleImageLabel sep = new ScaleImageLabel(lineImage);
    sep.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
    sep.setPreferredSize(new Dimension(Display.getInstance().getDisplayWidth(), 80)); // set the height of the separator
    photos.add(sep);} catch (IOException ex) {
    // handle the exception
}
        cn2.add(libelle_titre).add(titres);
        cn2.add(libelle_societe).add(nom);
        cn2.add(etat).add(e.getEtat());
        cn2.add(photos);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
  
        return cn1;
                
    
}
    
    
}
