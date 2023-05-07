/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Hend
 */
public class Utilisateur {
    private int id_utilisateur ;
    private String username, email, nom, adresse,biographie;
    private String mot_de_passe;
    private String num_tel;

    public Utilisateur() {
    }

    public Utilisateur(int id_utilisateur, String username, String email, String nom, String adresse, String biographie, String mot_de_passe, String num_tel) {
        this.id_utilisateur = id_utilisateur;
        this.username = username;
        this.email = email;
        this.nom = nom;
        this.adresse = adresse;
        this.biographie = biographie;
        this.mot_de_passe = mot_de_passe;
        this.num_tel = num_tel;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id_utilisateur=" + id_utilisateur + ", username=" + username + ", email=" + email + ", nom=" + nom + ", adresse=" + adresse + ", biographie=" + biographie + ", mot_de_passe=" + mot_de_passe + ", num_tel=" + num_tel + '}';
    }

   
   
}
