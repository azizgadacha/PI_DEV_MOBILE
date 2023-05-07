/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.Services.ServiceReponse;
import java.util.ArrayList;


/**
 *
 * @author Hend
 */
public class ListReponse extends Form {

    public ListReponse(Form previous){
        setTitle("List responses");
        setLayout(BoxLayout.y());
        ArrayList<Reponse> reponses = ServiceReponse.getInstance().getAllReponses();
        for (Reponse r : reponses) {
            addElement(r);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
   public void addElement(Reponse reponse) {
    Container row = new Container(new BorderLayout());
    Label reponseLabel = new Label(reponse.getReponse());
    Button deleteButton = new Button("Delete");
    deleteButton.addActionListener(e -> {
        ServiceReponse.getInstance().delete_reponse(reponse.getId_reponse());
        row.remove();
        revalidate();
    });
    row.add(BorderLayout.WEST, reponseLabel);
    row.add(BorderLayout.EAST, deleteButton);
    add(row);
}






}