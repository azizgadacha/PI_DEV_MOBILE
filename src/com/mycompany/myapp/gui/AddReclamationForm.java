/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author Hend
 */
public class AddReclamationForm extends Form {

    public AddReclamationForm(Form previous) {
        setTitle("Add New Reclamation");
        setLayout(BoxLayout.y());

        Label welcomeLabel = new Label("Please enter a title");
        TextField tfName = new TextField("", "Title");

        Label welcomeLabelType = new Label("Please enter a type");
        TextField tfType = new TextField("", "Type");

        Label welcomeLabelDesc = new Label("Please enter a description");
        TextField tfDesc = new TextField("", "Description");

        Button btnValider = new Button("Save");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0) || (tfType.getText().length() == 0) || (tfDesc.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Reclamation t = new Reclamation(tfName.getText(), tfType.getText(), tfDesc.getText());
                        if (ServiceReclamation.getInstance().addReclamation(t)) {
                            Dialog.show("Success", "Reclamation added successfully", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }
            }
        });

        addAll(welcomeLabel, tfName, welcomeLabelType, tfType, welcomeLabelDesc, tfDesc, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
