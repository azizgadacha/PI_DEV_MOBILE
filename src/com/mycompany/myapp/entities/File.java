
package com.mycompany.myapp.entities;


public class File {
    private int idFile;
    private int idUtilisateur;

    private byte [] cv ;
    private byte [] lettremotivation ;
    private byte [] deplome ;

    private String nameCV ;
    private String namelettreMotivation ;
    private String namedeplome ;


    public File(){
    }


    public int getIdFile() {
        return idFile;
    }

    public void setIdFile(int idFile) {
        this.idFile = idFile;
    }



    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }

    public byte[] getLettremotivation() {
        return lettremotivation;
    }

    public void setLettremotivation(byte[] lettremotivation) {
        this.lettremotivation = lettremotivation;
    }

    public byte[] getDeplome() {
        return deplome;
    }

    public void setDeplome(byte[] deplome) {
        this.deplome = deplome;
    }

    public String getNameCV() {
        return nameCV;
    }

    public void setNameCV(String nameCV) {
        this.nameCV = nameCV;
    }

    public String getNamelettreMotivation() {
        return namelettreMotivation;
    }

    public void setNamelettreMotivation(String namelettreMotivation) {
        this.namelettreMotivation = namelettreMotivation;
    }

    public String getNamedeplome() {
        return namedeplome;
    }

    public void setNamedeplome(String namedeplome) {
        this.namedeplome = namedeplome;
    }

    @Override
    public String toString() {
        return "File{" + "idFile=" + idFile + ", idUtilisateur=" + idUtilisateur + ", cv=" + cv + ", lettremotivation=" + lettremotivation + ", deplome=" + deplome + ", nameCV=" + nameCV + ", namelettreMotivation=" + namelettreMotivation + ", namedeplome=" + namedeplome + '}';
    }




}