/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui.User;

import com.codename1.io.Preferences;


public class SessionManager {

    public static Preferences pref;
    
    private static int id;

    public static void setId(int id) {
        pref.set("id", id);
       
        
    }

    public static int getId() {
        return pref.get("id", id);
    }
        int tel;
    
    private static String username;

    public static void setUserName(String username) {
        pref.set("username", username);
    }

    public static String getUserName() {
        return pref.get("username", username);
    }

    private static String password;

    public static void setPass(String password) {
        pref.set("password", password);
    }

    public static String getPass() {
        return pref.get("password", password);
    }

    private static String email;

    public static void setEmail(String email) {
        pref.set("email", email);
    }

    public static String getEmail() {
        return pref.get("email", email);
    }

    private static String niveau;
    public static void setNiveau(String image) {
        pref.set("niveau", image);
    }

    public static String getNiveau() {
        return pref.get("niveau", niveau);
    }
    
    private static String photo;
    public static void setPhoto(String photo){
        pref.set("photo",photo);
    }
    
    public static String getPhoto(){
        return pref.get("photo", photo);
    }

}