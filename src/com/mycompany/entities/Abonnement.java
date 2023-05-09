/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author ouesl
 */
public class Abonnement {
       
    private int id;
    private float prix;
    private String currency;
    private int plafond;
    private Date dateExpire;
    private int idUser;
    private String type;
    private boolean renouvellement ;

    public Abonnement(float prix, String currency, int plafond, Date dateExpire, int idUser, String type, boolean renouvellement) {
        this.prix = prix;
        this.currency = currency;
        this.plafond = plafond;
        this.dateExpire = dateExpire;
        this.idUser = idUser;
        this.type = type;
        this.renouvellement = renouvellement;
    }

    public Abonnement(int id, float prix, String currency, int plafond, Date dateExpire, int idUser, String type, boolean renouvellement) {
        this.id = id;
        this.prix = prix;
        this.currency = currency;
        this.plafond = plafond;
        this.dateExpire = dateExpire;
        this.idUser = idUser;
        this.type = type;
        this.renouvellement = renouvellement;
    }

    public Abonnement() {
    }

    public int getId() {
        return id;
    }

    public float getPrix() {
        return prix;
    }

    public String getCurrency() {
        return currency;
    }

    public int getPlafond() {
        return plafond;
    }

    public Date getDateExpire() {
        return dateExpire;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getType() {
        return type;
    }

    public boolean isRenouvellement() {
        return renouvellement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPlafond(int plafond) {
        this.plafond = plafond;
    }

    public void setDateExpire(Date dateExpire) {
        this.dateExpire = dateExpire;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRenouvellement(boolean renouvellement) {
        this.renouvellement = renouvellement;
    }
    
    
    
}
