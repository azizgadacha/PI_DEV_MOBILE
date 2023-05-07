/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.myapp.Services.CategorieService;
import com.mycompany.myapp.entities.categorie;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class ListCategorieForm extends Form {
    
    private ArrayList<categorie> categories;

    public ListCategorieForm() {
    super("Categories List");
        
        // Get all the velos from the server
         categories = CategorieService.getInstance().getAllcategories();
        
        // Create a container to hold the velos
        Container categorieContainer = new Container();
        categorieContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        categorieContainer.setScrollableY(true);        
        // Add each velo to the container with buttons to reserve, edit, and delete it
        for (categorie categories : categories) {
            // Create a container to hold the velo's information and buttons
            Container categorieRow = new Container(new BorderLayout());
            categorieRow.setUIID("CategorieBox");
            
       
            Label nomLabel = new Label("Nom Categorie: " + categories.getNom_categorie());
            nomLabel.setUIID("CategorieLabel");
           
            Button reserveBtn = new Button("Add");
            reserveBtn.addActionListener(e -> {
//                CategorieService.getInstance().addVelo(categories);
//          
            });
            Button editBtn = new Button("Edit");
            editBtn.addActionListener(e -> {
                //new EditCategorieForm(categories).show();
            });
            Button deleteBtn = new Button("Delete");
            deleteBtn.addActionListener(e -> {
                // Delete the velo from the server
                CategorieService.getInstance().deleteVelo(categories.getId_categorie());
                
                // Remove the velo from the container
                categorieContainer.removeComponent(categorieRow);
            });
            
            // Add the labels to the velo row
            Container labelsContainer = new Container(new GridLayout(5, 1));
            labelsContainer.add(nomLabel);

            
            categorieRow.add(BorderLayout.CENTER, labelsContainer);
            
            // Create a container to hold the buttons
            Container buttonsContainer = new Container(new GridLayout(3, 1));
            buttonsContainer.setUIID("categorieButtonBox");
            buttonsContainer.add(reserveBtn);
//            buttonsContainer.add(editBtn);
            buttonsContainer.add(deleteBtn);
            
            categorieRow.add(BorderLayout.EAST, buttonsContainer);
            
            // Add the velo row to the container
            categorieContainer.add(categorieRow);
        }
        
        // Add the container to the form
        add(categorieContainer);
        
        // Add a button to add a new velo
        Button addBtn = new Button("Add");
        Button editBtn = new Button("Edit");
        addBtn.addActionListener(e -> {
            new AddCategorieForm().show();
        });
        
//        avisBtn.addActionListener(e -> {
//            new AddAvisForm().show();
//        });
        
        add(addBtn);
        add(editBtn);
    }
    
}