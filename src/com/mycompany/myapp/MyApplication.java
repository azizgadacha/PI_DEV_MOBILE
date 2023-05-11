package com.mycompany.myapp;


import static com.codename1.ui.CN.*;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import com.mycompany.entities.Question;
import com.mycompany.gui.AjoutQuestionForm;
import com.mycompany.gui.ListeQuestionsForm;
import com.mycompany.gui.ModifierQuestionForm;



public class MyApplication {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        
        updateNetworkThreadCount(2);
        theme = UIManager.initFirstTheme("/theme");
        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        Question rec = new Question();
        //new AjoutQuestionForm(theme).show(); 
        new ListeQuestionsForm(theme).show();

        //Question rec = new Question("malik","feriel","07/05/2023","on hold","07/05/2023");
         //new ModifierQuestionForm(theme,rec).show();

    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
