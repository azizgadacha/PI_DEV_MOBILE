/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.List;
//import static javafx.scene.input.KeyCode.X;
//import static jdk.nashorn.internal.objects.NativeRegExp.test;
//import static services.QuestionCRUD.selectQuestionsByQuiz;
//import utils.MyDB;


public class Quiz {

    public static List<Quiz> selectQuizzes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private int id_quiz;
    int nb_question;
    public String sujet_quiz;
    public String bareme;
    public int state;


    public Quiz(int id_quiz, int nb_question, String sujet_quiz, String bareme) {
        this.id_quiz = id_quiz;
        this.nb_question = nb_question;
        this.sujet_quiz = sujet_quiz;
        this.bareme = bareme;

    }

    public Quiz(int id_quiz, int nb_question, String sujet_quiz, String bareme, int state) {
        this.id_quiz = id_quiz;
        this.nb_question = nb_question;
        this.sujet_quiz = sujet_quiz;
        this.bareme = bareme;
        this.state = state;
    }

    public Quiz(int id_quiz,String sujet_quiz) {
        this.id_quiz = id_quiz;
        this.sujet_quiz = sujet_quiz;

    }

    public Quiz() {}

    public Quiz(int nb_question, String sujet_quiz, String bareme) {
        this.nb_question = nb_question;
        this.sujet_quiz = sujet_quiz;
        this.bareme = bareme;
    }

    public int getId_quiz() {
        return id_quiz;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public int getNb_question() {
        return nb_question;
    }

    public String getSujet_quiz() {
        return sujet_quiz;
    }

    public String getBareme() {
       // List<Questions> questions = selectQuestionsByQuiz(id_quiz);
        String bareme = "";
       ///for (Questions question : questions) {
            //bareme += question.id_BonneReponse;
        //}



        return bareme;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
    }

    public void setNb_question(int nb_question) {
        this.nb_question = nb_question;
    }

    public void setSujet_quiz(String sujet_quiz) {
        this.sujet_quiz = sujet_quiz;
    }

    public void setBareme(String bareme) {
        this.bareme = bareme;
    }


    @Override
    public String toString() {
        return "Quiz{" + "id_quiz=" + id_quiz + ", nb_question=" + nb_question + ", sujet_quiz=" + sujet_quiz + ", bareme=" + bareme + '}';
    }







}