
package com.mycompany.myapp.gui;


import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Question;
import com.mycompany.myapp.entities.ServiceQuestion;
import com.mycompany.myapp.gui.ListeQuestionsForm;


public class AjoutQuestionForm extends Form {
    
    
    Form current;
    public AjoutQuestionForm(Resources res ) {
        super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
        setLayout(new FlowLayout(CENTER, CENTER));

        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        Container cn = new Container(BoxLayout.y());
        setTitle("Ajout Question");
        getContentPane().setScrollVisible(false);
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("logo.png"),"","",res);
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));


TextField question = new TextField("", "Entrer la question");
question.setUIID("TextFieldBlack");
addStringValue("Question", question);

TextField propositiona = new TextField("", "Entrer la proposition A");
propositiona.setUIID("TextFieldBlack");
addStringValue("Proposition A", propositiona);

TextField propositionb = new TextField("", "Entrer la proposition B");
propositionb.setUIID("TextFieldBlack");
addStringValue("Proposition B", propositionb);

TextField propositionc = new TextField("", "Entrer la proposition C");
propositionc.setUIID("TextFieldBlack");
addStringValue("Proposition C", propositionc);

TextField idBonnereponse = new TextField("", "Entrer l'ID de la bonne réponse");
idBonnereponse.setUIID("TextFieldBlack");
addStringValue("ID Bonne réponse", idBonnereponse);
        
        
   Button btnAjouter = new Button("Ajouter question");
        addStringValue("", btnAjouter);
        


        btnAjouter.addActionListener((e) -> {
            
            try {
                
                if(question.getText().equals("") || idBonnereponse.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                  //public Quiz k;
                  
                   Question r = new Question(
                                 question.getText(),
                                 propositiona.getText(),
                                 propositionb.getText(),
                                 propositionc.getText(),
                                 idBonnereponse.getText()
                                 
                                );
                    
                    System.out.println("data  Question == "+r);
                    
                    
                    ServiceQuestion.getInstance().ajoutQuestion(r);
                    new ListeQuestionsForm(res).show();
                    refreshTheme();
                }
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
        });

    }

public void addStringValue(String s, Component v) {
    Container cnt = new Container(new BorderLayout());
    cnt.add(BorderLayout.WEST, new Label(s, "PaddedLabel"));
    cnt.add(BorderLayout.CENTER, v);
    add(cnt);
}

      private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);

    }
     
}
