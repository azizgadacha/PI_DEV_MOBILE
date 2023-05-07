/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author acer
 */
public class candidature {
  private  int id_candidature;
    private Utilisateur utilisateur;
    private Annonce annonce;
    private float note;
    private String reponse;
    public candidature() {

    }
    public candidature(int id_candidature, Utilisateur utilisateur, Annonce annonce, float note, String reponse) {
        this.id_candidature = id_candidature;
        this.utilisateur = utilisateur;
        this.annonce = annonce;
        this.note = note;
        this.reponse = reponse;
    }
    public candidature(int id_candidature) {
        this.id_candidature = id_candidature;

    }

    public candidature(Utilisateur utilisateur, Annonce annonce, float note, String reponse) {
        this.utilisateur = utilisateur;
        this.annonce = annonce;
        this.note = note;
        this.reponse = reponse;
    }

    public int getId_candidature() {
        return id_candidature;
    }

    public void setId_candidature(int id_candidature) {
        this.id_candidature = id_candidature;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
  

