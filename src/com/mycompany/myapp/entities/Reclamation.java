/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Hend
 */

public class Reclamation {
    private Utilisateur Utilisateur;
    private int id_utilisateur,id_reclamation;
    private String description,titre;
    //private LocalDate date;
    private String type;
    private Date date;
    private String statut;

    public Reclamation() {
    }
    
    public Reclamation( Date date) {
        this.date = date;
    }
    
     public Reclamation(String description, String titre, String type) {
        this.titre = titre;
        this.type = type;
        this.date = date;
    }

    public Reclamation(Utilisateur Utilisateur, int id_utilisateur, int id_reclamation, String description, String titre, String type, Date date, String statut) {
        this.Utilisateur = Utilisateur;
        this.id_utilisateur = id_utilisateur;
        this.id_reclamation = id_reclamation;
        this.description = description;
        this.titre = titre;
        this.type = type;
        this.date = date;
        this.statut = statut;
    }

    public Utilisateur getUtilisateur() {
        return Utilisateur;
    }

    public void setUtilisateur(Utilisateur Utilisateur) {
        this.Utilisateur = Utilisateur;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "Utilisateur=" + Utilisateur + ", id_utilisateur=" + id_utilisateur + ", id_reclamation=" + id_reclamation + ", description=" + description + ", titre=" + titre + ", type=" + type + ", date=" + date + ", statut=" + statut + '}';
    }
 }