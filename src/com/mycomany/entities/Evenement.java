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
public class Evenement {
    
    
    
      private int idEvent;
    private String nomEvent;   
    private String descriptionEvent;   
    private String dateDebutEvent;   
    private String dateFinEvent;   
    private String lieuEvent;   
    private int budgetEvent;   
    private String image;   
    private float  prix;   
    private int  capacite;   

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }

    public String getDateDebutEvent() {
        return dateDebutEvent;
    }

    public void setDateDebutEvent(String dateDebutEvent) {
        this.dateDebutEvent = dateDebutEvent;
    }

    public String getDateFinEvent() {
        return dateFinEvent;
    }

    public void setDateFinEvent(String dateFinEvent) {
        this.dateFinEvent = dateFinEvent;
    }

    public String getLieuEvent() {
        return lieuEvent;
    }

    public void setLieuEvent(String lieuEvent) {
        this.lieuEvent = lieuEvent;
    }

    public int getBudgetEvent() {
        return budgetEvent;
    }

    public void setBudgetEvent(int budgetEvent) {
        this.budgetEvent = budgetEvent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Evenement() {
    }

    public Evenement(int idEvent, String nomEvent, String descriptionEvent, String dateDebutEvent, String dateFinEvent, String lieuEvent, int budgetEvent, String image, float prix, int capacite) {
        this.idEvent = idEvent;
        this.nomEvent = nomEvent;
        this.descriptionEvent = descriptionEvent;
        this.dateDebutEvent = dateDebutEvent;
        this.dateFinEvent = dateFinEvent;
        this.lieuEvent = lieuEvent;
        this.budgetEvent = budgetEvent;
        this.image = image;
        this.prix = prix;
        this.capacite = capacite;
    }

  
    
}
