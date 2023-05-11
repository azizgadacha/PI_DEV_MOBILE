/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;
import java.util.Date;




public class Question {
    private int idQuestion;
    private String question;
    private String propositiona;
    private String propositionb;
    private String propositionc;
    private String idBonnereponse;
    private Quiz id_quiz;

    public Question(int idQuestion, String question, String propositiona, String propositionb, String propositionc, String idBonnereponse, Quiz id_quiz) {
        this.idQuestion = idQuestion;
        this.question = question;
        this.propositiona = propositiona;
        this.propositionb = propositionb;
        this.propositionc = propositionc;
        this.idBonnereponse = idBonnereponse;
        this.id_quiz = id_quiz;
    }

    public Question(int idQuestion, String question, String propositiona, String propositionb, String propositionc, String idBonnereponse) {
        this.idQuestion = idQuestion;
        this.question = question;
        this.propositiona = propositiona;
        this.propositionb = propositionb;
        this.propositionc = propositionc;
        this.idBonnereponse = idBonnereponse;
    }
    
    

    public Question() {
    }

    public Question(String question, String propositiona, String propositionb, String propositionc, String idBonnereponse) {
        this.question = question;
        this.propositiona = propositiona;
        this.propositionb = propositionb;
        this.propositionc = propositionc;
        this.idBonnereponse = idBonnereponse;
    }
    
    
    
    

    public Question(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Question(String question, String propositiona, String propositionb, String propositionc, String idBonnereponse, Quiz id_quiz) {
        this.question = question;
        this.propositiona = propositiona;
        this.propositionb = propositionb;
        this.propositionc = propositionc;
        this.idBonnereponse = idBonnereponse;
        this.id_quiz = id_quiz;
    }

    @Override
    public String toString() {
        return "Question{" + "idQuestion=" + idQuestion + ", question=" + question + ", propositiona=" + propositiona + ", propositionb=" + propositionb + ", propositionc=" + propositionc + ", idBonnereponse=" + idBonnereponse + ", id_quiz=" + id_quiz + '}';
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public String getPropositiona() {
        return propositiona;
    }

    public String getPropositionb() {
        return propositionb;
    }

    public String getPropositionc() {
        return propositionc;
    }

    public String getIdBonnereponse() {
        return idBonnereponse;
    }

    public Quiz getId_quiz() {
        return id_quiz;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setPropositiona(String propositiona) {
        this.propositiona = propositiona;
    }

    public void setPropositionb(String propositionb) {
        this.propositionb = propositionb;
    }

    public void setPropositionc(String propositionc) {
        this.propositionc = propositionc;
    }

    public void setIdBonnereponse(String idBonnereponse) {
        this.idBonnereponse = idBonnereponse;
    }

    public void setId_quiz(Quiz id_quiz) {
        this.id_quiz = id_quiz;
    }
    
    
    
    
}
