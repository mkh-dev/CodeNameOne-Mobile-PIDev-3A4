/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author MALEK-ADMIN
 */
public class Reclamation {
    
    private int id;
    private String prenom;
    private String nom;
    private String message;

    public Reclamation() {
    }

    public Reclamation(int id, String prenom, String nom, String message) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.message = message;
    }

    public Reclamation(String prenom, String nom, String message) {
        this.prenom = prenom;
        this.nom = nom;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
