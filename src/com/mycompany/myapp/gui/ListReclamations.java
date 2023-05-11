/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;


import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.services.ServiceReponse;



/**
 *
 * @author Hend
 */
public class ListReclamations extends Form {

    public ListReclamations(Form previous) {
        setTitle("List reclamations");
        setLayout(BoxLayout.y());
        ArrayList<Reclamation> reclamations = ServiceReclamation.getInstance().getAllReclamations();
        for (Reclamation r : reclamations) {
            addElement(r);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Reclamation reclamation) {
        Container reclamationContainer = new Container(new BorderLayout());
        System.out.println(reclamation);
        Label titleLabel = new Label(reclamation.getTitre());
        Style titleStyle = titleLabel.getUnselectedStyle();
        titleStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        titleLabel.setUnselectedStyle(titleStyle);

        Label typeLabel = new Label(reclamation.getType());
        Style typeStyle = typeLabel.getUnselectedStyle();
        typeStyle.setFgColor(0x6495ED); // set the color to red
        typeLabel.setUnselectedStyle(typeStyle);

       Button showButton = new Button("View Response");
        showButton.addActionListener(e -> {
            Reclamation selectedReclamation = reclamation;
            if (selectedReclamation != null) {
                showReponse(this, selectedReclamation.getId_reclamation());
            }
        });

Button deleteBtn = new Button("Delete");
            deleteBtn.addActionListener(e -> {
                // Delete the velo from the server
                ServiceReclamation.getInstance().deleteVelo(reclamation.getId_reclamation());
                

                 new ListReclamations(this).show();
            });
        
        Container buttonContainer = new Container(new GridLayout(1, 2));
        buttonContainer.add(showButton);
        buttonContainer.add(deleteBtn);

        Container rowContainer = new Container(new BorderLayout());
        rowContainer.add(BorderLayout.WEST, titleLabel);
        rowContainer.add(BorderLayout.EAST, buttonContainer);

        reclamationContainer.add(BorderLayout.NORTH, rowContainer);
        reclamationContainer.add(BorderLayout.SOUTH, typeLabel);

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
