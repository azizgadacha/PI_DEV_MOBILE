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
public class categorie {
private int id_categorie;
    private String nom_categorie;

    public categorie() {
    }

    public categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
      
    }

    public categorie(int aInt, String string) {
        this.id_categorie = aInt;
        this.nom_categorie = string;


    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    @Override
    public String toString() {
        return "categorie{" + "id_categorie=" + id_categorie + ", nom_categorie=" + nom_categorie + '}';
    }

   

  

    public void setNom(categorie categorie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
