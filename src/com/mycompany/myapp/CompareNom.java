package com.mycompany.myapp;


import java.util.Comparator;
import logo.entities.candidature;

public class CompareNom implements Comparator<candidature> {
    public int compare(candidature o1, candidature o2) {
        return  (o1.getUtilisateur().getUsername().compareTo(o2.getUtilisateur().getUsername()));
    }
}
