/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;


public class Role {
    
    private int id_role;
    private String nom_role,description;

    public Role() {
    }

    public Role(int id_role, String nom_role, String description) {
        this.id_role = id_role;
        this.nom_role = nom_role;
        this.description = description;
    }public Role(int id_role) {
        this.id_role = id_role;

    }public Role(int id_role,String nom_role) {
        this.id_role = id_role;
        this.nom_role = nom_role;

    }

    public Role(String nom_role, String description) {
        this.nom_role = nom_role;
        this.description = description;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getNom_role() {
        return nom_role;
    }

    public void setNom_role(String nom_role) {
        this.nom_role = nom_role;
    }

   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" + "id_role=" + id_role + ", nom_role=" + nom_role + ", description=" + description + '}';
    }

   
     
}
