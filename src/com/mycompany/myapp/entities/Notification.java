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
public class Notification {
     private int id_notification, id_utilisateur;
    private String description;
    private Date date;

    public Notification() {
    }

    public Notification(int id_notification, int id_utilisateur, String description, Date date) {
        this.id_notification = id_notification;
        this.id_utilisateur = id_utilisateur;
        this.description = description;
        this.date = date;
    }

    public int getId_notification() {
        return id_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Notification{" + "id_notification=" + id_notification + ", id_utilisateur=" + id_utilisateur + ", description=" + description + ", date=" + date + '}';
    }
    
    
}
