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
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import logo.entities.candidature;
import logo.entities.rendez_vous;

/**
 *
 * @author ilyes
 */
public class RendezVousListForm extends Form {
    
    private ArrayList<rendez_vous> RendezVous;

    public RendezVousListForm() {
    super("Users List");
        
        // Get all the velos from the server
         RendezVous = RendezVousService1.getInstance().getAllVelos();
        System.out.println("eeeerezrzerez");
        System.out.println("eeeerezrzerez"+RendezVous.size());
        // Create a container to hold the velos
        Container CandidatureContainer = new Container();
        CandidatureContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        CandidatureContainer.setScrollableY(true);        
        // Add each velo to the container with buttons to reserve, edit, and delete it
        
        
        
        for (rendez_vous user : RendezVous) {
            // Create a container to hold the velo's information and buttons
            Container CandidatureRow = new Container(new BorderLayout());
            CandidatureRow.setUIID("Username");
            
            // Create labels to display the velo's information
            Label idLabel = new Label("Heure " + user.getHeure_rendez_vous());
                       idLabel.setUIID("Heure");
                       Date date = new Date(); // example date object
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // example date format
        String dateString = formatter.format(date);

            Label passwordLabel = new Label("Date: " + dateString);
            passwordLabel.setUIID("Date");
           
            
            
            // Create buttons to reserve, edit, and delete the velo
            
            Button editBtn = new Button("Edit");
            editBtn.addActionListener(e -> {
                 RendezVouEdit form =  new RendezVouEdit(user) ;
        form.EditRendezVous(user);
            // new EditVeloForm(user).show();
            });
            Button deleteBtn = new Button("Refuser");
            deleteBtn.addActionListener(e -> {
              RendezVousService1.getInstance().deleteVelo(user.getId_rendez_vous());
              CandidatureContainer.removeComponent(CandidatureRow);
               CandidatureContainer.revalidate();
CandidatureContainer.repaint();
// RendezVousListForm form1 =  new RendezVousListForm() ;        
                //form1.showNourrituresList();
                //Remove the velo from the container
            });
            
            // Add the labels to the velo row
            Container labelsContainer = new Container(new GridLayout(5, 1));
            labelsContainer.add(idLabel);
            labelsContainer.add(passwordLabel);
           
            
            CandidatureRow.add(BorderLayout.CENTER, labelsContainer);
            
            // Create a container to hold the buttons
            Container buttonsContainer = new Container(new GridLayout(3, 1));
            buttonsContainer.setUIID("CandidatureButtonBox");
            buttonsContainer.add(editBtn);
            buttonsContainer.add(deleteBtn);
            
            CandidatureRow.add(BorderLayout.EAST, buttonsContainer);
            
            // Add the velo row to the container
            CandidatureContainer.add(CandidatureRow);
             CandidatureContainer.revalidate();
CandidatureContainer.repaint();
        }
        
        // Add the container to the form
        add(CandidatureContainer);
         CandidatureContainer.revalidate();
CandidatureContainer.repaint();
        
        // Add a button to add a new velo
        //addBtn.addActionListener(e -> {
            //new AddVeloForm().show();
        //});
        
       
        
       // add(addBtn);
       // add(avisBtn);
    }
      public void showNourrituresList() {
                  Toolbar.setGlobalToolbar(true);

    RendezVousListForm form = new RendezVousListForm();
    Toolbar tb=form.getToolbar();
  tb.addMaterialCommandToSideMenu("side bar", FontImage.MATERIAL_WEB, (ActionListener )->{
                  new RendezVousListForm().show();

  });
  tb.addMaterialCommandToSideMenu("Settuing", FontImage.MATERIAL_SETTINGS, (ActionListener )->{ });
      
    form.show();
    
}
}