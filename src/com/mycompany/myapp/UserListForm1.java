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
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Service.user.ServiceUtilisateur;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.entities.rendez_vous;
import com.mycompany.myapp.gui.UpdateForm;
import com.mycompany.myapp.gui.User.ModifierUtilisateur;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ilyes
 */
public class UserListForm1 extends Form {
    
    private ArrayList<Utilisateur> RendezVous;
        private Resources theme;

    public UserListForm1( Form previous) {
    super("Users List");
        
        // Get all the velos from the server
         RendezVous = ServiceUtilisateur.getInstance().getAllUsers();
        System.out.println("eeeerezrzerez");
        System.out.println("eeeerezrzerez"+RendezVous.size());
        // Create a container to hold the velos
        Container CandidatureContainer = new Container();
        CandidatureContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        CandidatureContainer.setScrollableY(true);        
        // Add each velo to the container with buttons to reserve, edit, and delete it
        
        
        
        for (Utilisateur user : RendezVous) {
            // Create a container to hold the velo's information and buttons
            Container CandidatureRow = new Container(new BorderLayout());
            CandidatureRow.setUIID("Username");
            
            // Create labels to display the velo's information
           

            Label passwordLabel = new Label("Email: " + user.getEmail());
            passwordLabel.setUIID("email");
           
            Label username = new Label("Username: " + user.getUsername());
            passwordLabel.setUIID("email");
           
            
            
            // Create buttons to reserve, edit, and delete the velo
            
          
            Button deleteBtn = new Button("supprimer");
            deleteBtn.addActionListener(e -> {
                
ServiceUtilisateur.getInstance().deleteUser(user.getId()); 
CandidatureContainer.removeComponent(CandidatureRow);
               CandidatureContainer.revalidate();
CandidatureContainer.repaint();/*
                RendezVousService1.getInstance().deleteVelo(user.getId_rendez_vous());
              CandidatureContainer.removeComponent(CandidatureRow);
               CandidatureContainer.revalidate();
CandidatureContainer.repaint();
// RendezVousListForm form1 =  new RendezVousListForm() ;  */      
                //form1.showNourrituresList();
                //Remove the velo from the container
            });
            
            // Add the labels to the velo row
            Container labelsContainer = new Container(new GridLayout(5, 1));
            labelsContainer.add(username);
            labelsContainer.add(passwordLabel);
           
            
            CandidatureRow.add(BorderLayout.CENTER, labelsContainer);
            
            // Create a container to hold the buttons
            Container buttonsContainer = new Container(new GridLayout(3, 1));
            buttonsContainer.setUIID("CandidatureButtonBox");
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
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        
        // Add a button to add a new velo
        //addBtn.addActionListener(e -> {
            //new AddVeloForm().show();
        //});
        
       
        
       // add(addBtn);
       // add(avisBtn);
    }
     
}