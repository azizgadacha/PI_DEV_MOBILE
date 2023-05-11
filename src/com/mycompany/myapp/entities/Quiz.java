
package com.mycompany.myapp.entities;


public class Quiz {
    public int idQuiz;
    public int nombreQuestions;
    public String barem;
    public String sujetQuiz;
    public Utilisateur id_utilisateur;

    public Quiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public Quiz() {
    }
    
    
}
