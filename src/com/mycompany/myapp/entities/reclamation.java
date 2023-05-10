/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Hend
 */
public class reclamation {
    private int id;
    private String description,titre,type;

    public reclamation(int id, String description, String titre, String type) {
        this.id = id;
        this.description = description;
        this.titre = titre;
        this.type = type;
    }

    public reclamation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", description=" + description + ", titre=" + titre + ", type=" + type + '}';
    }
    
    
}
