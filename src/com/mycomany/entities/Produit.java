/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author youss
 */
public class Produit {
    
    
      private int idProd;
    private String nomProd;   
    private String description;   
    private double prix;   
    private int quantite;   
    private String nomPart;   
    private String image;   
    private int nbLikes;   
      private int  nbDislikes;   

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomPart() {
        return nomPart;
    }

    public void setNomPart(String nomPart) {
        this.nomPart = nomPart;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbLikes() {
        return nbLikes;
    }

    public void setNbLikes(int nbLikes) {
        this.nbLikes = nbLikes;
    }

    public int getNbDislikes() {
        return nbDislikes;
    }

    public void setNbDislikes(int nbDislikes) {
        this.nbDislikes = nbDislikes;
    }

    public Produit(int idProd, String nomProd, String description, double prix, int quantite, String nomPart, String image, int nbLikes, int nbDislikes) {
        this.idProd = idProd;
        this.nomProd = nomProd;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.nomPart = nomPart;
        this.image = image;
        this.nbLikes = nbLikes;
        this.nbDislikes = nbDislikes;
    }

    public Produit() {
    }
      
      
      
      
      
      
    
}
