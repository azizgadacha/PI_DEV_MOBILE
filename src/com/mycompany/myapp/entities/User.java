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
public class User {
    private int ID;
    private String Email,Password,username,address;
    private int age;

    public User(int ID, String Email, String Password, String username, String address, int age) {
        this.ID = ID;
        this.Email = Email;
        this.Password = Password;
        this.username = username;
        this.address = address;
        this.age = age;
    }

    public User(String Email, String Password, String username, String address, int age) {
        this.Email = Email;
        this.Password = Password;
        this.username = username;
        this.address = address;
        this.age = age;
    }

    public User() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}