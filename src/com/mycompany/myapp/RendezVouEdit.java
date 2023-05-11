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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.rendez_vous;
import java.text.SimpleDateFormat;
import java.util.Date;




/**
 *
 * @author nourh
 */




public class RendezVouEdit extends Form {
rendez_vous rdv=new rendez_vous();

    public RendezVouEdit(rendez_vous rdv,Form previous) {  
        super("dqds", BoxLayout.y());
                this.rdv=rdv;

        

                                    Label lbDate =new Label("choisir une date de rendez ");
                                    Label lbHeure =new Label("choisir uheure de rendez vous");

                Picker datePicker = new Picker();
                                        datePicker.setType(Display.PICKER_TYPE_DATE);
datePicker.setDate(rdv.getDate_rendez_vous());
                        Container cntainerHeure = new Container(BoxLayout.x());

ComboBox heure=new ComboBox();
for(int i =1;i<=24;i++){
heure.addItem(""+i);

}
        System.out.println("dddd "+(rdv.getHeure_rendez_vous()));
        System.out.println("dddd "+(rdv.getHeure_rendez_vous()).substring(0, 2));
        System.out.println("dddd "+(rdv.getHeure_rendez_vous()).substring(0, 3));
        System.out.println("dddd "+(rdv.getHeure_rendez_vous()).substring(0, 1));
        System.out.println("dddd "+(rdv.getHeure_rendez_vous()).substring(3, 5));
        System.out.println("dddd "+(rdv.getHeure_rendez_vous()).substring(2, 5));
        System.out.println("ahla"+ String.valueOf( Integer.parseInt((rdv.getHeure_rendez_vous()).substring(0, 2))-1));
  int testing=Integer.parseInt((rdv.getHeure_rendez_vous()).substring(0, 2))-1;
        System.out.println("bir");      
        System.out.println(testing);      
  heure.setSelectedIndex(    testing);
        System.out.println(  "eeefeff  ");
        System.out.println(  heure.getSelectedItem());
    ComboBox min=new ComboBox();
for(int i =1;i<=59;i++)
min.addItem(""+i);
heure.setSelectedIndex(    Integer.parseInt((rdv.getHeure_rendez_vous()).substring(3, 5))-1);
        cntainerHeure.add(heure );

        
cntainerHeure.add(min);

    

        // Create text fields and a combo box for the evenement information
      
        
       

        // Create a button to add the evenement
        Button addBtn = new Button("Edit");
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
                                rendezvous.setId_rendez_vous(rdv.getId_rendez_vous());

                                rendezvous.setHeure_rendez_vous(HeureString);
                    System.out.println("ezezez "+HeureString);
rendezvous.setAnnonce(rdv.getAnnonce());
rendezvous.setUser(rdv.getUser());
                // Create a new evenement with the entered information
             
                

                // Add the evenement to the server
                RendezVousService1.getInstance().editVelo(rendezvous);
                new RendezVousListForm( previous).show();
                }
                // Close the form
            } catch (NumberFormatException ex) {
                // Handle the case where the entered values are not valid integers
                Dialog.show("Error", "Please enter valid integer values for ID and User ID, and a valid decimal value for Price", "OK", null);
            }
        });
        

        // Add the text fields and button to the form
        addAll(lbDate,datePicker,lbHeure,cntainerHeure,addBtn);
                        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
  

    private void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}