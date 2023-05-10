/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author ferie
 */
public class Typereclamation {
   private int id;
    private String typereclamation;

    public Typereclamation() {
    }

    public Typereclamation(int id, String typereclamation) {
        this.id = id;
        this.typereclamation = typereclamation;
    }

    public Typereclamation(String typereclamation) {
        this.typereclamation = typereclamation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypereclamation() {
        return typereclamation;
    }

    public void setTypereclamation(String typereclamation) {
        this.typereclamation = typereclamation;
    }

    @Override
    public String toString() {
        return "Typereclamation{" + "id=" + id + ", typereclamation=" + typereclamation + '}';
    }
    
    
}
