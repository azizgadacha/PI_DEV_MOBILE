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
import com.codename1.ui.Font;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.services.ServiceReponse;



/**
 *
 * @author Hend
 */
public class ListReclamations extends Form{
    
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

    Label titleLabel = new Label(reclamation.getTitre());
    Style titleStyle = titleLabel.getUnselectedStyle();
    titleStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
    titleLabel.setUnselectedStyle(titleStyle);

    Label typeLabel = new Label(reclamation.getType());
    Style typeStyle = typeLabel.getUnselectedStyle();
    typeStyle.setFgColor(0xFF0000); // set the color to red
    typeLabel.setUnselectedStyle(typeStyle);

    Button showButton = new Button("View Response");
    showButton.addActionListener(e -> {
    Reponse reponse = ServiceReponse.getInstance().getReponse(reclamation.getId_reclamation());
    new ShowReponse(this, reponse).show();
});


    Container rowContainer = new Container(new BorderLayout());
    rowContainer.add(BorderLayout.WEST, titleLabel);
    rowContainer.add(BorderLayout.EAST, showButton);

    reclamationContainer.add(BorderLayout.NORTH, rowContainer);
    reclamationContainer.add(BorderLayout.SOUTH, typeLabel);

    add(reclamationContainer);
}




  
}

    

