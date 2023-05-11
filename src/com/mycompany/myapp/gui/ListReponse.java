/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.services.ServiceReponse;
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
        Container reclamationContainer = new Container(new BorderLayout());
        System.out.println(reponse);
        Label titleLabel = new Label(reponse.getReponse());
        Style titleStyle = titleLabel.getUnselectedStyle();
        titleStyle.setFgColor(0x6495ED);
        titleStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        titleLabel.setUnselectedStyle(titleStyle);

       
     
Button deleteBtn = new Button("Delete Reponse");
            deleteBtn.addActionListener(e -> {
                // Delete the velo from the server
                ServiceReponse.getInstance().deleteVelo(reponse.getId_reponse());
                

                 new ListReponse(this).show();
            });
        
        Container buttonContainer = new Container(new GridLayout(1, 2));
        //buttonContainer.add(showButton);
        buttonContainer.add(deleteBtn);

        Container rowContainer = new Container(new BorderLayout());
        rowContainer.add(BorderLayout.WEST, titleLabel);
        rowContainer.add(BorderLayout.EAST, buttonContainer);

        reclamationContainer.add(BorderLayout.NORTH, rowContainer);
        //reclamationContainer.add(BorderLayout.SOUTH, typeLabel);

        add(reclamationContainer);
    }

    private void showReponse(Form previous, int id_reclamation) {
    Reponse reponse = ServiceReponse.getInstance().getReponse(id_reclamation);
    if (reponse != null) {
        Form showReponse = new ShowReponse(previous, reponse);
        showReponse.show();
    } else {
        Dialog.show("Error", "Unable to retrieve response for this reclamation", "OK", null);
    }
}







}

