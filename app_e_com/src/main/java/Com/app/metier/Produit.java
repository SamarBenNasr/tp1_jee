package Com.app.metier;

import java.math.BigDecimal;

public class Produit {

    private int id;
    private String nom;
    private String description;
    private  BigDecimal prix;
    private String image;

    // Getters and setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // Default constructor
    public Produit() {}

    // Constructor with parameters
    public Produit(int id, String nom, String description, BigDecimal prix, String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image = image;
    }
}
