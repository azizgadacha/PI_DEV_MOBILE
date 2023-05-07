package logo.entities;

import logo.entities.Annonce;
import logo.entities.Utilisateur;

public class Postulation {

private Annonce annonce;
private Utilisateur utilisateur;
private String etat ;
private File file;

    public Postulation(Annonce annonce, Utilisateur utilisateur, String etat, String file) {
        this.annonce = annonce;
        this.utilisateur = utilisateur;
        this.etat = etat;
    }
    public Postulation(Annonce annonce, Utilisateur utilisateur, String etat, File file) {
        this.annonce = annonce;
        this.utilisateur = utilisateur;
        this.etat = etat;
        this.file=file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public File getFile() {
        return file;
    }






}
