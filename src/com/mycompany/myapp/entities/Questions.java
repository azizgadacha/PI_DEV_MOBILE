/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;


public class Questions {
    public int id_Question;
    private int id_Quiz;
    public String question;
    public String propositionA;
    public String propositionB;
    public String propositionC;
    public String id_BonneReponse;

    public Questions(int id_Question, int id_Quiz, String question, String propositionA, String propositionB, String propositionC, String id_BonneReponse) {
        this.id_Question = id_Question;
        this.id_Quiz = id_Quiz;
        this.question = question;
        this.propositionA = propositionA;
        this.propositionB = propositionB;
        this.propositionC = propositionC;
        this.id_BonneReponse = id_BonneReponse;
    }

    public Questions(String question) {

        this.question = question;

    }

    public Questions() {
    }

    public Questions(String question, String propositionA, String propositionB, String propositionC, String id_BonneReponse) {
        this.question = question;
        this.propositionA = propositionA;
        this.propositionB = propositionB;
        this.propositionC = propositionC;
        this.id_BonneReponse = id_BonneReponse;
    }



    public Questions(int id_Quiz, String question, String propositionA, String propositionB, String propositionC, String id_BonneReponse) {
        this.id_Quiz = id_Quiz;
        this.question = question;
        this.propositionA = propositionA;
        this.propositionB = propositionB;
        this.propositionC = propositionC;
        this.id_BonneReponse = id_BonneReponse;
    }




    public int getId_Question() {
        return id_Question;
    }

    public int getId_Quiz() {
        return id_Quiz;
    }

    public String getQuestion() {
        return question;
    }

    public String getPropositionA() {
        return propositionA;
    }

    public String getPropositionB() {
        return propositionB;
    }

    public String getPropositionC() {
        return propositionC;
    }

    public String getId_BonneReponse() {
        return id_BonneReponse;
    }

    public void setId_Question(int id_Question) {
        this.id_Question = id_Question;
    }

    public void setId_Quiz(int id_Quiz) {
        this.id_Quiz = id_Quiz;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setPropositionA(String propositionA) {
        this.propositionA = propositionA;
    }

    public void setPropositionB(String propositionB) {
        this.propositionB = propositionB;
    }

    public void setPropositionC(String propositionC) {
        this.propositionC = propositionC;
    }

    public void setId_BonneReponse(String id_BonneReponse) {
        this.id_BonneReponse = id_BonneReponse;
    }

    @Override
    public String toString() {
        return "Questions{" + "id_Question=" + id_Question + ", id_Quiz=" + id_Quiz + ", question=" + question + ", propositionA=" + propositionA + ", propositionB=" + propositionB + ", propositionC=" + propositionC + ", id_BonneReponse=" + id_BonneReponse + '}';
    }




}