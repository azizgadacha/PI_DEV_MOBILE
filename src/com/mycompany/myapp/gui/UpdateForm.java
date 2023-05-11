package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Service.CategorieService;
import com.mycompany.myapp.entities.categorie;


public class UpdateForm extends Form {
    categorie c = new categorie();
    private TextField nomcatField;
   public UpdateForm(Form previous, int idc){
        c= CategorieService.getInstance().getCategoryById(idc);
       Button verifbtn = new  Button("test");
        nomcatField = new TextField(c.getNom_categorie(), "Nom");
        add(nomcatField);
       add(verifbtn);
       verifbtn.addActionListener(e -> {
              System.out.println(c.toString());
               categorie c2 = new categorie();
               c2.setId_categorie(idc);
              c2.setNom_categorie(nomcatField.getText()); 
              CategorieService.getInstance().UpdateCategorie(c2);
               
                   });
  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}
}