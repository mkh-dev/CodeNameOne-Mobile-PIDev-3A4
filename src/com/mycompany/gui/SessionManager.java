/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class SessionManager {
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String prenom ; 
private static String nom ; 
private static String email ; 
private static Date datenaissance ; 
private static int numtel ; 
private static String userrole ; 
private static String password ; 

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SessionManager.id = id;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        SessionManager.prenom = prenom;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        SessionManager.nom = nom;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    public static Date getDatenaissance() {
        return datenaissance;
    }

    public static void setDatenaissance(Date datenaissance) {
        SessionManager.datenaissance = datenaissance;
    }

    public static int getNumtel() {
        return numtel;
    }

    public static void setNumtel(int numtel) {
        SessionManager.numtel = numtel;
    }

    public static String getUserrole() {
        return userrole;
    }

    public static void setUserrole(String userrole) {
        SessionManager.userrole = userrole;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SessionManager.password = password;
    }

    
    
    
    
}
