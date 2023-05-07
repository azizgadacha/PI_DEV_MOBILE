/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompany.myapp.Services.CategorieService;
import com.mycompany.myapp.entities.categorie;

/**
 *
 * @author acer
 */
public class EditCategorieForm  extends Form {
//      
//public EditCategorieForm(int id_categorie) {
//    super("Edit categorie");
    
    // Retrieve the category object by ID
    //categorie categorie = CategorieService.getInstance().getCategoryById(id_categorie);
    
    // Create a text field for the category name and set its value to the current name
    //TextField nomField = new TextField(categorie.getNom_categorie(), "nomCategorie");
    
    // Create a button to modify the category
//    Button editBtn = new Button("Edit");
//    editBtn.addActionListener(e -> {
//        // Get the new name from the text field
//        String newNom = nomField.getText().trim();
//        if (newNom.isEmpty()) {
//            Dialog.show("Error", "Name cannot be empty", "OK", null);
//            return;
//        }
        
        // Modify the category object with the new name
      //  categorie.setNom_categorie(newNom);
        
        // Call the service to update the category
//        if (CategorieService.getInstance().updateCategorie(categorie)) {
//            Dialog.show("Success", "Category updated successfully", "OK", null);
//            // Go back to the previous form
//            this.showBack();
//        } else {
//            Dialog.show("Error", "Failed to update category", "OK", null);
////        }
//    });
//    
//    // Add the text fields and button to the form
//    add(nomField);
//    add(editBtn);
//}
}
