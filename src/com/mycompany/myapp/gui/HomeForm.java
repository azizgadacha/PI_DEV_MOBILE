/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.Container;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.CandidatureListForm;
import com.mycompany.myapp.RendezVousListForm;
import com.mycompany.myapp.gui.User.ListUtilisateurs;

/**
 *
 * @author Hend
 */
public class HomeForm extends Form {
        private Resources theme;

     //   theme = UIManager.initFirstTheme("/theme");
    
    public HomeForm( Resources res) {
        super("Match Talent");
        
        Toolbar.setGlobalToolbar(true);

        // add content to the form
        


        getToolbar().addCommandToLeftSideMenu("Reclamations", null, e-> new ListReclamations(this).show());
        //getToolbar().addCommandToLeftSideMenu("Add Reclamation", null, e-> new AddReclamationForm(this).show());
      getToolbar().addCommandToLeftSideMenu("List Of Responses", null, e->  new ListReponse(this).show());   
    //  getToolbar().addCommandToLeftSideMenu("Reply", null, e-> new AddReponseForm(this).show());
    getToolbar().addCommandToLeftSideMenu("list categories", null, e->
            
            new ListCategorieForm(this).show()); 
    getToolbar().addCommandToLeftSideMenu("list User", null, e->
            
            new ListUtilisateurs( res).show()); 
    getToolbar().addCommandToLeftSideMenu("Candidature", null, e-> {
            CandidatureListForm form =  new CandidatureListForm( this) ;
       form. show();});
    getToolbar().addCommandToLeftSideMenu("Question", null, e-> {
               new AjoutQuestionForm(theme).show();   
});
        getToolbar().addCommandToLeftSideMenu("Add categories", null, e-> new AddCategorieForm().show());
        getToolbar().addCommandToLeftSideMenu("Liste Rendez vous", null, e->{
            RendezVousListForm form =  new RendezVousListForm( this) ;
       form. show();
        });
 
    }
}
