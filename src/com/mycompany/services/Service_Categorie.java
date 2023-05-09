/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Categorie;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Service_Categorie {
     public ArrayList<Categorie> Coachs;
    public static Service_Categorie instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Categorie() {
        req = new ConnectionRequest();
    }

     
    public static Service_Categorie getInstance() {
        if (instance == null) {
            instance = new Service_Categorie();
        }
        return instance;
    }
    
    
    
    
    
     public ArrayList<Categorie> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Categorie coach = new Categorie();
                
                
                

               float idcategorie = Float.parseFloat(obj.get("idcategorie").toString());
               coach.setIdcategorie((int) idcategorie);
               
             
        
                
                
        
               coach.setNomcategorie(obj.get("nomcategorie").toString());
               
               
               coach.setGenre(obj.get("genre").toString());
               
         
     


                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<Categorie> findAll() {
        String url = Statics.BASE_URL + "cat_mboile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Coachs = parseCoach(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Coachs;
    }
    
    
    
    
    
    
    
    
    
    
    
          public void AddCatl(Categorie c) {
        String url = Statics.BASE_URL + "newcat_mobile/"+ c.getNomcategorie()+ "/" + c.getGenre(); //création de l'URL

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
   
    }
    
    
    
    
    
    
    
    
    
    public boolean deleteCat(int idcategorie) {
        String url = Statics.BASE_URL + "SupprimerCat?idcategorie=" + idcategorie;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
                    
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOK;
    }
    
    
    
    
    
    
    
    
    
    
    public boolean ModifierCat( Categorie c) {
        
       String url = Statics.BASE_URL + "updateCat?idcategorie=" + c.getIdcategorie()+"&nomcategorie=" + c.getNomcategorie()+"&genre=" + c.getGenre();

       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code success  http 200 ok
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        //System.out.println("url ==> " + url);
        //System.out.println("data ==> " + req);
        return resultOK;

    }

    
    
    
    

    
    
                  
    
    
    
    
     
            
   
    
    
     
}
