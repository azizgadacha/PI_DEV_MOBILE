/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Hend
 */

public class Utilisateur {



    private int id;
    private String username,mot_de_passe,email,contact,address,biographie,nom_societe;
    private Role role;
    public Utilisateur() {
    }
    public Utilisateur(int id) {
        this.id = id;}
    //new utilisateur(resultat.getInt("id"),resultat.getString("username"),resultat.getString("email"),resultat.getString("contact"),resultat.getString("address"))
    public Utilisateur(int id,  String username, String email, String contact, String address) {
        this.id = id;
        this.username = username;
        this.mot_de_passe = mot_de_passe;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.biographie = biographie;
        this.nom_societe = nom_societe;
    }
    public Utilisateur(int id, int id_role, String username, String mot_de_passe, String email, String contact, String address, String biographie, String nom_societe) {
        this.id = id;
        this.username = username;
        this.mot_de_passe = mot_de_passe;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.biographie = biographie;
        this.nom_societe = nom_societe;
    } public Utilisateur(int id, int id_role, String username, String mot_de_passe, String email, String contact, String address, String biographie, String nom_societe,Role r) {
        this.id = id;
        this.role = new Role(id_role);
        this.username = username;
        this.mot_de_passe = mot_de_passe;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.biographie = biographie;
        this.nom_societe = nom_societe;
        this.role=r;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

//utilisateur(resultat.getInt("id_user"),resultat.getString("username")),
public Utilisateur(int id, String username,String email,String contact, String address, String biographie, String nom_societe ) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.contact = contact;
    this.address = address;
    this.biographie = biographie;
    this.nom_societe = nom_societe;

}

    public Utilisateur(int id, String username) {
        this.id = id;
        this.username = username;

    }

    public Utilisateur(int id_role, String username, String mot_de_passe, String email, String contact, String address, String biographie, String nom_societe) {
        this.role = new Role(id_role);
        this.username = username;
        this.mot_de_passe = mot_de_passe;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.biographie = biographie;
        this.nom_societe = nom_societe;
    }




    public Utilisateur(int id,String username,String email) {
        this.username =username;
        this.email = email;
    }
    public Utilisateur(String email, String mot_de_passe) {
        this.email = email;
        this.mot_de_passe = mot_de_passe;
    }






    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public String getNom_societe() {
        return nom_societe;
    }

    public void setNom_societe(String nom_societe) {
        this.nom_societe = nom_societe;
    }

    @Override

    public String toString() {
        return "Utilisateur{" + "id=" + id + ", id_role=" + role.getId_role() + ", username=" + username + ", mot_de_passe=" + mot_de_passe + ", email=" + email + ", contact=" + contact + ", address=" + address + ", biographie=" + biographie + ", nom_societe=" + nom_societe + '}';
    }



}




/*public class utilisateur {
    private int id_utilisateur ;
    private String username, email, nom, adresse,biographie;
    private String mot_de_passe;
    private String num_tel;

    public utilisateur() {
    }

    public utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public utilisateur(String username) {
        this.username = username;


    }

    public utilisateur(int id_utilisateur, String username, String email) {
        this.id_utilisateur = id_utilisateur;
        this.username = username;
        this.email = email;

    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    @Override
    public String toString() {
        return "utilisateur{" + "id_utilisateur=" + id_utilisateur + ", username=" + username + ", email=" + email + ", nom=" + nom + ", adresse=" + adresse + ", biographie=" + biographie + ", mot_de_passe=" + mot_de_passe + ", num_tel=" + num_tel + '}';
    }

}*/
