/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Asus
 */
public class Hotel {
    
   
    private int id;
    private String nom;
    private int nbrEtoile;
    private String lieux;
    private String image;
        private String email;
        
       private int prixlpd;
        private int prixpc;
         private int prixdp;

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

    public int getNbrEtoile() {
        return nbrEtoile;
    }

    public void setNbrEtoile(int nbrEtoile) {
        this.nbrEtoile = nbrEtoile;
    }

    public String getLieux() {
        return lieux;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPrixlpd() {
        return prixlpd;
    }

    public void setPrixlpd(int prixlpd) {
        this.prixlpd = prixlpd;
    }

    public int getPrixpc() {
        return prixpc;
    }

    public void setPrixpc(int prixpc) {
        this.prixpc = prixpc;
    }

    public int getPrixdp() {
        return prixdp;
    }

    public void setPrixdp(int prixdp) {
        this.prixdp = prixdp;
    }




    
}
