package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import static com.codename1.contacts.ContactsManager.refresh;
import com.codename1.ui.Command;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.myapp.entities.Question;
import com.mycompany.myapp.entities.ServiceQuestion;


 public class ListeQuestionsForm extends BaseForm {
     Form current;
    public ListeQuestionsForm(Resources res ) {
          super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
        Toolbar tb = new Toolbar(true);
        //current = this ;
        //setToolbar(tb);
        //getTitleArea().setUIID("Container");
        //setTitle("Liste Questions");
        //getContentPane().setScrollVisible(false);
 
        
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
       // FlowLayout flow = new FlowLayout(CENTER);
        //flow.setValign(BOTTOM);
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
        //add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Refresh", barGroup);
        mesListes.setUIID("SelectBar");
        
        RadioButton stats = RadioButton.createToggle("stats", barGroup);
        stats.setUIID("SelectBar");
        
        RadioButton ajouter = RadioButton.createToggle("Ajouter", barGroup);
        ajouter.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
         ListeQuestionsForm a = new ListeQuestionsForm(res);
            a.show();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, stats, ajouter),
                FlowLayout.encloseBottom(arrow)
        ));

       ajouter.addActionListener((e) -> {
               
                new AjoutQuestionForm(res).show();   
       });
        stats.addActionListener((e) -> {
               
                new StatistiquePieForm(res).show();   
       });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(stats, arrow);
        bindButtonSelection(ajouter, arrow);
        
      
        //Appel affichage methode
        ArrayList<Question>list = ServiceQuestion.getInstance().affichageQuestions();
        System.out.println("hrlllo"+list.size());
        System.out.println("hffffrlllo"+list.get(0).getPropositiona());
        for(Question Q : list ) {
             String urlImage ="logo.png";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
             URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             
                addButton(urlim,Q,res);
        
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
        
        
        
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
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
         int LEFT;
        
   //avoire
   l.getUnselectedStyle().setMargin(50, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }

    private void addButton(Image img,Question Q , Resources res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        

        Container cnt = new Container();
        cnt.setLayout(new BorderLayout());
       
        Label QuestionTxt= new Label("question: "+Q.getQuestion(),"NewsTopLine2");
        Label propostionaTxt= new Label("propostion A: "+Q.getPropositiona(),"NewsTopLine2");
        Label propostionbTxt= new Label("propostion B: "+Q.getPropositionb(),"NewsTopLine2" );
        Label propostioncTxt= new Label("propostion C: "+Q.getPropositionc(),"NewsTopLine2" );
        Label BonnereponseTxt= new Label("Id bonne réponse: "+Q.getIdBonnereponse(),"NewsTopLine2" );
        
                
                
        createLineSeparator();
        
         
        //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer cette question ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Question
                if(ServiceQuestion.getInstance().deleteQuestion(Q)) {
                  Dialog.show("Success", "Connection accepted", new Command("OK"));
                  refresh();
                    //new ListeQuestionsForm(res).show();
                }
           
        });
          cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                
                BoxLayout.encloseX(QuestionTxt),
                BoxLayout.encloseX(propostionaTxt),
                BoxLayout.encloseX(propostionbTxt),
                BoxLayout.encloseX(propostioncTxt),
                BoxLayout.encloseX(BonnereponseTxt)
          ));
  
        add(cnt);
    }
      
      
  

 }


  

