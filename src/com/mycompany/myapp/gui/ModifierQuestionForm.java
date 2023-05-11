
package com.mycompany.myapp.gui;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.myapp.entities.Question;
import com.mycompany.myapp.entities.ServiceQuestion;
import com.mycompany.myapp.gui.ListeQuestionsForm;



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
        
        
       
        
        TextField question = new TextField(r.getQuestion() , "question" , 20 , TextField.ANY);
        TextField propositiona = new TextField(r.getPropositiona() , "propositiona" , 20 , TextField.ANY);
        TextField propositionb = new TextField(r.getPropositionb() , "propositionb" , 20 , TextField.ANY);
        TextField propositionc = new TextField(r.getPropositionc() , "propositionc" , 20 , TextField.ANY);
        TextField idBonnereponse = new TextField(r.getIdBonnereponse() , "idBonnereponse" , 20 , TextField.ANY);

        
        
        
        question.setUIID("NewsTopLine");
        propositiona.setUIID("NewsTopLine");
        propositionb.setUIID("NewsTopLine");
        propositionc.setUIID("NewsTopLine");
        idBonnereponse.setUIID("NewsTopLine");
        
        
       
        
        question.setSingleLineTextArea(true);
        //description.setSingleLineTextArea(true);
       
               
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((question.getText().length() == 0) || (propositiona.getText().length() == 0) || (propositionb.getText().length() == 0) || (propositionc.getText().length() == 0) || (idBonnereponse.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Question Questionupdated = new Question(question.getText(), propositiona.getText(), propositionb.getText(), propositionc.getText(), idBonnereponse.getText());

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
                  new FloatingHint(question),
                  createLineSeparator(),
                  new FloatingHint(propositiona),
                  createLineSeparator(),
                  new FloatingHint(propositionb),
                  createLineSeparator(),
                  new FloatingHint(propositionc),
                  createLineSeparator(),
                  new FloatingHint(idBonnereponse),
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
