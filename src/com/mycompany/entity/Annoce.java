/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class Annoce {
    
    private int id ;
    private String titre,nomSociete ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    @Override
    public String toString() {
        return "Annoce{" + "id=" + id + ", titre=" + titre + ", nomSociete=" + nomSociete + '}';
    }
    
    
    
    
}
