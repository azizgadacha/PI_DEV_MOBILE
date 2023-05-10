/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

/**
 *
 * @author sanabenfadhel
 */
public class User {
    private int id;
    private String nom,email,nomimg;
    private int age,tel;

    public User(int id, String nom, String email, String nomimg, int age, int tel) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.nomimg = nomimg;
        this.age = age;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomimg() {
        return nomimg;
    }

    public void setNomimg(String nomimg) {
        this.nomimg = nomimg;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", nomimg=" + nomimg + ", age=" + age + ", tel=" + tel + '}';
    }
    
    
}
