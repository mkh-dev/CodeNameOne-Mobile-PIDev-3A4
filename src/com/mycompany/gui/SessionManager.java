/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;


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
        return pref.get("id",id);
    }

    public static void setId(int id) {
        pref.set("id",id);
    }

    public static String getPrenom() {
       return pref.get("prenom",prenom);
    }

    public static void setPrenom(String prenom) {
       pref.set("prenom",prenom);
    }

    public static String getNom() {
       return pref.get("nom",nom);
    }

    public static void setNom(String nom) {
        pref.set("nom",nom);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
        pref.set("email",email);
    }

public static void setDatenaissance(Date datenaissance) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateStr = dateFormat.format(datenaissance);
    pref.set("datenaissance", dateStr);
}

public static Date getDatenaissance() {
    String dateStr = pref.get("datenaissance", null);
    if (dateStr == null) {
        return null;
    } else {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException ex) {
            return null;
        }
    }
}


    public static int getNumtel() {
       return pref.get("numtel",numtel);
    }

    public static void setNumtel(int numtel) {
        pref.set("numtel",numtel);
    }

    public static String getUserrole() {
        return pref.get("userrole",userrole);
    }

    public static void setUserrole(String userrole) {
        pref.set("userrole",userrole);
    }

    public static String getPassword() {
        return pref.get("password",password);
    }

    public static void setPassword(String password) {
        pref.set("password",password);
    }

    
    
    
    
}

