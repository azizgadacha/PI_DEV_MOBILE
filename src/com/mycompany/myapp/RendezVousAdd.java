/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.text.SimpleDateFormat;
import java.util.Date;
import logo.entities.candidature;
import logo.entities.rendez_vous;



/**
 *
 * @author nourh
 */




public class RendezVousAdd extends Form {
candidature rdv=new candidature();

    public RendezVousAdd(candidature rdv) {  
        super("dqds", BoxLayout.y());
                this.rdv=rdv;

        

                                    Label lbDate =new Label("choisir une date de rendez ");
                                    Label lbHeure =new Label("choisir uheure de rendez vous");

                Picker datePicker = new Picker();
                                        datePicker.setType(Display.PICKER_TYPE_DATE);

                        Container cntainerHeure = new Container(BoxLayout.x());

ComboBox heure=new ComboBox();
for(int i =1;i<=24;i++)
heure.addItem(""+i);
    
    ComboBox min=new ComboBox();
for(int i =1;i<=59;i++)
min.addItem(""+i);
        cntainerHeure.add(heure );

cntainerHeure.add(min);

    

        // Create text fields and a combo box for the evenement information
      
        
       

        // Create a button to add the evenement
        Button addBtn = new Button("Add");
        addBtn.addActionListener(e -> {

            // Validate the entered values
            try {
                String HeureString = ((String)(heure.getSelectedItem()))+':'+((String)(min.getSelectedItem()));
                System.out.println( datePicker.getDate());
                
                Date dateGet = (Date)datePicker.getDate();

                Date dateAuj=new Date();
                
                boolean res=false;
                try{
                    
               SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
String dateString = formatter.format(dateGet);
Date date = formatter.parse(dateString);
//if(date.compareTo(dateAuj)>0)
  //      res=false;
//else
  //  res=true;
    

                }catch(Exception ex){
                    System.out.println(ex);
                }
                System.out.println("reee "+res);
               // =dateGet. before(dateAuj);
                        int num = Integer.parseInt((String)(heure.getSelectedItem()));
                if(
                        //(res)||
                        (num>18)||(num<8)){
                    Dialog d = new Dialog();
        d.show("Refuser", "la date ou l'heure est invalid", "ok","close");
        
                }else{
                
                
                                rendez_vous rendezvous = new rendez_vous();
                                rendezvous.setDate_rendez_vous(dateGet);
rendezvous.setHeure_rendez_vous(HeureString);
                    System.out.println("ezezez "+HeureString);
rendezvous.setAnnonce(rdv.getAnnonce());
rendezvous.setUser(rdv.getUtilisateur());
                // Create a new evenement with the entered information
             
                

                // Add the evenement to the server
                RendezVousService1.getInstance().addVelo(rendezvous);
                new RendezVousListForm().show();
                }
                // Close the form
            } catch (NumberFormatException ex) {
                // Handle the case where the entered values are not valid integers
                Dialog.show("Error", "Please enter valid integer values for ID and User ID, and a valid decimal value for Price", "OK", null);
            }
        });
        

        // Add the text fields and button to the form
        addAll(lbDate,datePicker,lbHeure,cntainerHeure,addBtn);
    }
      public void AddRendezVous(candidature rdv) {
                       this.rdv=rdv;


    RendezVousAdd form = new RendezVousAdd(rdv);
    form.show();
    
}

    private void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}