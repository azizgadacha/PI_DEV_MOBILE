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
public class reclamation {
   private int id;
    private String description,titre,type;

    public reclamation(int id, String description, String titre, String type) {
        this.id = id;
        this.description = description;
        this.titre = titre;
        this.type = type;
    }

    public reclamation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", description=" + description + ", titre=" + titre + ", type=" + type + '}';
    }
    
    
}
