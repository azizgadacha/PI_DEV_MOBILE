
package com.mycompany.gui;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.mycompany.services.ServiceQuestion;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.mycompany.entities.Question;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;



public class ModifierQuestionForm extends BaseForm {
      Form current;
    public ModifierQuestionForm(Resources res , Question r) {
        // super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Question");
        getContentPane().setScrollVisible(false);
        
        
        //super.addSideMenu(res);
        
        TextField title = new TextField(r.getTitle() , "title" , 20 , TextField.ANY);
        TextField description = new TextField(r.getDescription() , "Description" , 20 , TextField.ANY);
        //TextField etat = new TextField(String.valueOf(r.getEtat()) , "Etat" , 20 , TextField.ANY);
 
        title.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
       // etat.setUIID("NewsTopLine");
        
        title.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
       // etat.setSingleLineTextArea(true);
       
       // Créez un objet ComboBox vide
//ComboBox Typereclamation = new ComboBox<>();
                    
// Récupérez la liste des types de réclamations en appelant votre méthode affichageTypes()
//ArrayList<Typereclamation> types = ServiceQuestion.getInstance().affichageTypes();
// for (Typereclamation t : types) {
 //   Typereclamation.addItem(t.getTypereclamation());
//}     
// Ajoutez l'objet ComboBox à mon formulaire
//addStringValue("Type de réclamation", Typereclamation);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((title.getText().length() == 0) || (description.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Question Questionupdated = new Question(r.getQuestion(),title.getText(), description.getText(),r.getDateCreation(),r.getEtat(),r.getDateTreatment(),r.getNote(),null, null);
                        if (ServiceQuestion.getInstance().modifierQuestion(Questionupdated)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                            //new ModifierQuestionForm(ListeQuestionsForm(res),reclamationupdated).show();
                            new ListeQuestionsForm(res).show();

                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }

            private Resources ListeQuestionsForm(Resources res) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
      
       Label l2 = new Label("");
        Label l1 = new Label();
        
        Container content;
          content = BoxLayout.encloseY(
                  l1, l2,
                  new FloatingHint(title),
                  createLineSeparator(),
                  new FloatingHint(description),
                  createLineSeparator(),
                
                  btnModifier
               //   btnAnnuler
                  
                  
          );
        
        add(content);
        show();
        
        
    }
    
    public void addStringValue(String s, Component v) {
    Container cnt = new Container(new BorderLayout());
    cnt.add(BorderLayout.WEST, new Label(s, "PaddedLabel"));
    cnt.add(BorderLayout.CENTER, v);
    add(cnt);
}
    
}
