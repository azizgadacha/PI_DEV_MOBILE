/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Service.ServiceReponse;
import com.mycompany.myapp.entities.Reponse;
import java.util.ArrayList;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 *
 * @author Hend
 */

public class ShowReponse extends Form {
    private final Reponse reponse;

    public ShowReponse(Form previous, Reponse reponse) {
                
        this.reponse = reponse;
        setTitle("Reponse Details");
        setLayout(new BorderLayout());
        System.out.println("gfdgfd"+reponse.getId_reclamation());

        TextArea reponseTextArea = new TextArea(reponse.getReponse());
        reponseTextArea.setEditable(false);

        //Label dateLabel = new Label("Date: " + reponse.getDate().toString());

        Container contentPane = getContentPane();
        contentPane.add(BorderLayout.NORTH, reponseTextArea);
        //contentPane.add(BorderLayout.SOUTH, dateLabel);

        getToolbar().addCommandToLeftBar("Back", null, evt -> previous.showBack());
    }
    
    private void showReponse(Form previous) {
    Reponse reponse = ServiceReponse.getInstance().getReponse(1);
    if (reponse != null) {
        Form showReponse = new ShowReponse(previous, reponse);
        showReponse.show();
    } else {
        Dialog.show("Error", "Unable to retrieve reponse for this reclamation", "OK", null);
    }
}

}