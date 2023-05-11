package com.mycompany.services;

import com.mycompany.entities.Question;
import com.mycompany.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class ServiceQuestion {

    //singleton 
    public static ServiceQuestion instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceQuestion getInstance() {
        if (instance == null) {
            instance = new ServiceQuestion();
        }
        return instance;
    }

    public ServiceQuestion() {
        req = new ConnectionRequest();
        //ConnectionRequest.setDefaultCacheMode(ConnectionRequest.CachingMode.OFF);//desactiver cache des requetes 

    }

    //ajout 
    public void ajoutQuestion(Question Q) {

        String url = Statics.BASE_URL + "/newjson?question=" + Q.getQuestion() + "&propositiona=" + Q.getPropositiona() + "&propositionb=" + Q.getPropositionb() + "&propositionc=" + Q.getPropositionc() + "&idBonnereponse=" + Q.getIdBonnereponse();

        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());//appel lel resultat de json
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution de la requete 

    }

    public ArrayList<Question> affichageQuestions() {
        ArrayList<Question> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/backquestion_JSON";

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> mapQuestions = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapQuestions.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Question re = new Question();

                        float idQuestion = Float.parseFloat(obj.get("idQuestion").toString());
                        re.setIdQuestion((int) idQuestion);
                        System.out.println("ena lina" + idQuestion);
                        String question = obj.get("question").toString();
                        re.setQuestion(question);

                        String propositiona = obj.get("propositiona").toString();
                        re.setPropositiona(propositiona);

                        String propositionb = obj.get("propositionb").toString();
                        re.setPropositionb(propositionb);

                        String propositionc = obj.get("propositionc").toString();
                        re.setPropositionc(propositionc);

                        String idBonnereponse = obj.get("idBonnereponse").toString();
                        re.setIdBonnereponse(idBonnereponse);

                        result.add(re);

                    }
                } catch (Exception ex) {
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    }

    //Update 
    public boolean modifierQuestion(Question question) {
        String url = Statics.BASE_URL + "/updateQjson/" + question.getIdQuestion()
                + "?&testquestion=" + question.getQuestion()
                + "&propositiona=" + question.getPropositiona()
                + "&propositionb=" + question.getPropositionb()
                + "&propositionc=" + question.getPropositionc()
                + "&idBonnereponse=" + question.getIdBonnereponse();
//"&id_quiz=" + question.getId_quiz();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

    }

    public boolean deleteQuestion(Question Q) {
    String url = Statics.BASE_URL + "/deleteQjson/" +Q.getIdQuestion();
   // ConnectionRequest req = new ConnectionRequest();

    req.setUrl(url);
        NetworkManager.getInstance().addToQueue(req);

     //req.addArgument("id", String.valueOf(Q.getIdQuestion()));
  /* req.addResponseListener((evt) -> {
                   System.out.println("ccccccccccccccccccc");

        if (req.getResponseCode() == 200) {
            
            resultOk = true;
        } else {
            resultOk = false;
        }
    });*/
    return resultOk;
}
/*
    public boolean deleteQuestion(Question Q) {
    String url = Statics.BASE_URL + "/deleteQjson/" + Q.getIdQuestion();
        req.setUrl(url);

 req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
 NetworkManager.getInstance().addToQueue(req);
        return true;
    }*/
    
    
    /*
     public void deleteQuestion(Question Q) {

    String url = Statics.BASE_URL + "/deleteQjson/" + Q.getIdQuestion();

        req.setUrl(url);
        req.addResponseListener((e) -> {
           // String str = new String(req.getResponseData());//appel lel resultat de json
           // System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution de la requete 

    }

    
    */
    
    
    
    
    
  /*  
*/
}
