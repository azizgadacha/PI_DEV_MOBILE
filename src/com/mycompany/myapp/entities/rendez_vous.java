package com.mycompany.myapp.entities;

import java.util.Date;

public class rendez_vous {
    private int id_rendez_vous;
    private Utilisateur user ;
    private Date date_rendez_vous;
    private String Heure_rendez_vous;
    private Annonce annonce;

    public rendez_vous(int id_rendez_vous, Utilisateur user, Date date_rendez_vous, String heure_rendez_vous, Annonce annonce) {
        this.id_rendez_vous=id_rendez_vous;
        this.user = user;
        this.date_rendez_vous = date_rendez_vous;
        Heure_rendez_vous = heure_rendez_vous;
        this.annonce = annonce;
    } public rendez_vous(Utilisateur user, Date date_rendez_vous, String heure_rendez_vous,Annonce annonce) {
        this.user = user;
        this.date_rendez_vous = date_rendez_vous;
        Heure_rendez_vous = heure_rendez_vous;
        this.annonce = annonce;
    }
    public rendez_vous(int id_rendez_vous) {
        this.id_rendez_vous=id_rendez_vous;


    }public rendez_vous() {
       


    }

    public rendez_vous(int id_rendez_vous, Date date_rendez_vous, String heure_rendez_vous) {
        this.id_rendez_vous=id_rendez_vous;
        this.date_rendez_vous = date_rendez_vous;
        Heure_rendez_vous = heure_rendez_vous;

    }

    public rendez_vous( Date date_rendez_vous, String heure_rendez_vous, Annonce annonce) {
        this.date_rendez_vous = date_rendez_vous;
        Heure_rendez_vous = heure_rendez_vous;
        this.annonce = annonce;
    }

    public int getId_rendez_vous() {
        return id_rendez_vous;
    }

    public void setId_rendez_vous(int id_rendez_vous) {
        this.id_rendez_vous = id_rendez_vous;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Date getDate_rendez_vous() {
        return date_rendez_vous;
    }

    public void setDate_rendez_vous(Date date_rendez_vous) {
        this.date_rendez_vous = date_rendez_vous;
    }

    public String getHeure_rendez_vous() {
        return Heure_rendez_vous;
    }

    public void setHeure_rendez_vous(String heure_rendez_vous) {
        Heure_rendez_vous = heure_rendez_vous;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }
}
