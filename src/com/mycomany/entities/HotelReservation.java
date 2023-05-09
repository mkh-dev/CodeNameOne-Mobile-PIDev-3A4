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
public class HotelReservation {
    
      private int id;
    private String dateArrive;
    
        private String dateDepart;
        private String typeChambre;
        private String typeReservation;
      private int idh;
      private int iduser;

    public int getNbrnuite() {
        return nbrnuite;
    }

    public void setNbrnuite(int nbrnuite) {
        this.nbrnuite = nbrnuite;
    }

    public int getPrixtot() {
        return prixtot;
    }

    public void setPrixtot(int prixtot) {
        this.prixtot = prixtot;
    }
      
            private int nbrnuite;
            private int prixtot;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(String dateArrive) {
        this.dateArrive = dateArrive;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(String typeChambre) {
        this.typeChambre = typeChambre;
    }

    public String getTypeReservation() {
        return typeReservation;
    }

    public void setTypeReservation(String typeReservation) {
        this.typeReservation = typeReservation;
    }

    public int getIdh() {
        return idh;
    }

    public void setIdh(int idh) {
        this.idh = idh;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

}
