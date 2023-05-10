/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import logo.entities.candidature;

/**
 *
 * @author ilyes
 */
public class CandidatureListForm extends Form {
    Resources theme;
    private ArrayList<candidature> candidatures;

    public void remplir(Container CandidatureContainer,ArrayList<Container> containerListeListe){
        
        for (candidature user : candidatures) {
            System.out.println("rani   n3awid  n3abi");
            // Create a container to hold the velo's information and buttons
            Container CandidatureRow = new Container(new BorderLayout());
            
            CandidatureRow.setUIID("Username"+user.getId_candidature());
            
            // Create labels to display the velo's information
            Label idLabel = new Label("name " + user.getUtilisateur().getUsername());
                       idLabel.setUIID("NoteLabel");

            Label passwordLabel = new Label("Note: " + user.getNote());
            passwordLabel.setUIID("NoteLabel");
           
            
            
            // Create buttons to reserve, edit, and delete the velo
            Button MakeRDV = new Button("Add");
            MakeRDV.addActionListener(e -> {
                RendezVousAdd form =  new RendezVousAdd(user) ;
        form.AddRendezVous(user);
            });
            Button editBtn = new Button("Edit");
            editBtn.addActionListener(e -> {
             //RendezVousAdd form =  new RendezVousAdd() ;
       // form.AddRendezVous();
            });
            Button deleteBtn = new Button("Refuser");
            deleteBtn.addActionListener(e -> {
               CandidatureService.getInstance().deleteVelo(user.getId_candidature());
              CandidatureListForm form =  new CandidatureListForm( theme) ;
       form.showNourrituresList(theme);
                // Remove the velo from the container
                System.out.println("rrrzzzzdzdz");
                System.out.println("rrrzzzzdzdz"+CandidatureRow.getName());
                System.out.println("rrrzzzzdzdz"+CandidatureRow.getUIID());
                System.out.println("rrrz"+containerListeListe.get(0).getUIID());
                System.out.println("rrrz"+containerListeListe.get(0).getUIID());
               int i=0;
                System.out.println("lombo "+i);
                while((!(containerListeListe.get(i).getUIID().equals("Username"+user.getId_candidature())))&&(i<containerListeListe.size())){
                    i++;
                    System.out.println("ahla "+i);
                }
                
               CandidatureContainer.removeComponent(containerListeListe.get(i));
            });
            
            // Add the labels to the velo row
            Container labelsContainer = new Container(new GridLayout(5, 1));
            labelsContainer.add(idLabel);
            labelsContainer.add(passwordLabel);
           
            
            CandidatureRow.add(BorderLayout.CENTER, labelsContainer);
            
            // Create a container to hold the buttons
            Container buttonsContainer = new Container(new GridLayout(3, 1));
            buttonsContainer.setUIID("CandidatureButtonBox");
            buttonsContainer.add(MakeRDV);
            buttonsContainer.add(editBtn);
            buttonsContainer.add(deleteBtn);
            
            CandidatureRow.add(BorderLayout.EAST, buttonsContainer);
            
            // Add the velo row to the container
            
            containerListeListe.add(CandidatureRow);
            CandidatureContainer.add(CandidatureRow);
            CandidatureContainer.revalidate();
CandidatureContainer.repaint();
        }
        
    }
    
    
    
    public CandidatureListForm(Resources theme ) {
        
    super("Users List");
          Font boldFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
            ArrayList<Container> containerListeListe=new ArrayList<Container>();

        // Get all the velos from the server
         candidatures = CandidatureService.getInstance().getAllVelos();
               Container CandidatureContainer = new Container();
        CandidatureContainer.setLayout(new BoxLayout(BoxLayout. Y_AXIS));
            Button Trie = new Button("Trier");
            Trie.getAllStyles().setFgColor(0x0000ff);

            Trie.addActionListener(e->{
                                System.out.println("im here2");

                Collections.sort(candidatures, new CompareNom());
                for(int i=0;i<candidatures.size();i++){
                    System.out.println("yellar"+candidatures.get(i).getUtilisateur().getUsername());
                }
                System.out.println( containerListeListe.size());
                for (int j=0;j<containerListeListe.size();j++){
CandidatureContainer.removeComponent(containerListeListe.get(j));
                CandidatureContainer.revalidate();
CandidatureContainer.repaint();
                System.out.println("irani fasa5t  a" );}
                                System.out.println("irani kamalt  a" );

                remplir(CandidatureContainer,containerListeListe);
            });
        // Create a container to hold the velos
 
                    Label HeaderLabel = new Label("liste candidature");
                    Container HeadContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                 HeaderLabel.getUnselectedStyle().setFont(boldFont);
         HeadContainer.addAll(HeaderLabel);
         remplir(CandidatureContainer,containerListeListe);
                                

        // Add each velo to the container with buttons to reserve, edit, and delete it
        
       
        // Add the container to the form
                                       add(Trie);
add(HeadContainer);
        add(CandidatureContainer);
        
        // Add a button to add a new velo
        //addBtn.addActionListener(e -> {
            //new AddVeloForm().show();
        //});
        
       
        
       // add(addBtn);
       // add(avisBtn);
    }
      public void showNourrituresList(Resources theme) {
          this.theme=theme;
    CandidatureListForm form = new CandidatureListForm(theme);
    Toolbar tb=form.getToolbar();
  tb.addMaterialCommandToSideMenu("side bar", FontImage.MATERIAL_WEB, (ActionListener )->{
                  new RendezVousListForm().show();

  });
  tb.addMaterialCommandToSideMenu("Settuing", FontImage.MATERIAL_SETTINGS, (ActionListener )->{ });
         
    form.show();
    
}
}