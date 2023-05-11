/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Service.CategorieService;
import com.mycompany.myapp.entities.categorie;

/**
 *
 * @author acer
 */
public class AddCategorieForm extends Form {
      
public AddCategorieForm (Form previous) {
        super("Add categorie");
        
  
        TextField nomField = new TextField("", " nomCategorie");
  


        // Create a button to add the velo
        Button addBtn = new Button("Add");
        addBtn.addActionListener(e -> {
            // Validate the entered values
            try {
         
                String  nom_categorie = nomField.getText().trim();
              

                // Check that the entered values are valid
                if ( nom_categorie.isEmpty()  ) {
                    Dialog.show("Error", "Please enter valid values for Nom Categorie", "OK", null);
                    return;
                }

                // Create a categorie user with the entered information
                categorie categorie = new categorie();
                categorie.setNom_categorie(nom_categorie);

                // Add the categorie to the server
                CategorieService.getInstance().addVelo(categorie);
                 new ListCategorieForm(previous).show(); 
                //new AddCategorieForm().show();

                // Close the form
               // this.close();
            } catch (NumberFormatException ex) {
                // Handle the case where the entered values are not valid integers
                Dialog.show("Error", "Please enter valid integer values for ID, Station ID, and Category ID", "OK", null);
            }
        });
        
        // Add the text fields and combo box to the form
        add(nomField);
     
  
        add(addBtn);
     
    }

  
    
}