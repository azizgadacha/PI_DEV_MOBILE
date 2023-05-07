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
public class Reponse {
      private Reclamation Reclamation;
    private int id_reponse, id_reclamation;
    private String reponse;
    private Date date;

    public Reponse() {
    }

    public Reponse(Reclamation Reclamation, int id_reponse, int id_reclamation, String reponse, Date date) {
        this.Reclamation = Reclamation;
        this.id_reponse = id_reponse;
        this.id_reclamation = id_reclamation;
        this.reponse = reponse;
        this.date = date;
    }
    
     public Reponse(Date date) {
        this.date = date;
    }
public Reponse(String reponse) {
        this.reponse = reponse;
    }

    public Reclamation getReclamation() {
        return Reclamation;
    }

    public void setReclamation(Reclamation Reclamation) {
        this.Reclamation = Reclamation;
    }

    public int getId_reponse() {
        return id_reponse;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reponse{" + "Reclamation=" + Reclamation + ", id_reponse=" + id_reponse + ", id_reclamation=" + id_reclamation + ", reponse=" + reponse + ", date=" + date + '}';
    }
    
    
}
