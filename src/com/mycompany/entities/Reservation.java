/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author ouesl
 */
public class Reservation {
        private int numRes;
       private String idUser;
       private int nbPlaces;

    public void setNumRes(int numRes) {
        this.numRes = numRes;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public int getNumRes() {
        return numRes;
    }

    public String getIdUser() {
        return idUser;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public Reservation(int numRes, String idUser, int nbPlaces, String idEvent) {
        this.numRes = numRes;
        this.idUser = idUser;
        this.nbPlaces = nbPlaces;
        this.idEvent = idEvent;
    }
       private String idEvent;

    public Reservation() {
    }

    
   
       
       
           @Override
    public String toString() {
        return "Task{" + "numRes=" + numRes + ", idEvent=" + idEvent + ", idUser=" + idUser + ", nbPlaces=" + nbPlaces + "\n";
    }
       
}
