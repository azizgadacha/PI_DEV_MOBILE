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
public class File {
    
    
    private int id , id_user;
    private String lettreM , cv,deplome ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLettreM() {
        return lettreM;
    }

    public void setLettreM(String lettreM) {
        this.lettreM = lettreM;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getDeplome() {
        return deplome;
    }

    public void setDeplome(String deplome) {
        this.deplome = deplome;
    }

    @Override
    public String toString() {
        return "File{" + "id=" + id + ", id_user=" + id_user + ", lettreM=" + lettreM + ", cv=" + cv + ", deplome=" + deplome + '}';
    }
    
    
    
    
    
}
