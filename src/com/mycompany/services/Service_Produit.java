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
import com.mycomany.entities.Produit;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author youss
 */
public class Service_Produit {
        
    public ArrayList<Produit> Coachs;
    public static Service_Produit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Produit() {
        req = new ConnectionRequest();
    }

     
    public static Service_Produit getInstance() {
        if (instance == null) {
            instance = new Service_Produit();
        }
        return instance;
    }
    
    
    
    
    
     public ArrayList<Produit> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Produit coach = new Produit();
                
                
                

               float idProd = Float.parseFloat(obj.get("idProd").toString());
               coach.setIdProd((int) idProd);
               
             
                float prix = Float.parseFloat(obj.get("prix").toString());
               coach.setPrix((double) prix);
               
               
       
               
                
               float quantite = Float.parseFloat(obj.get("quantite").toString());
               coach.setQuantite((int) quantite);
               
                
                
        
               coach.setNomProd(obj.get("nomProd").toString());
               
               
               coach.setDescription(obj.get("description").toString());
               
               coach.setNomPart(obj.get("nomPart").toString());

                 coach.setImage(obj.get("image").toString());

         
                 

               float nbLikes = Float.parseFloat(obj.get("nbLikes").toString());
               coach.setQuantite((int) nbLikes);
               
               
               float nbDislikes = Float.parseFloat(obj.get("nbDislikes").toString());
               coach.setNbDislikes((int) nbDislikes);
               


                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<Produit> findAll() {
        String url = Statics.BASE_URL + "produit_mobile";
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
    
    
    
    
    
    
    
    
    
    
    
      
          public void AddProduit(Produit c) {
        String url = Statics.BASE_URL + "newproduit_mobile/"+ c.getNomProd()+ "/" + c.getDescription()+ "/" +c.getPrix()+ "/" + c.getQuantite()+ "/" +c.getNomPart()+ "/" + c.getImage()+ "/" +c.getNbLikes()+ "/" +c.getNbDislikes(); //création de l'URL

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
    
    
    
    
    
    
    
      public boolean deleteProduit(int idProd) {
        String url = Statics.BASE_URL + "SupprimerProduit?idProd=" + idProd;
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
    
      
      
      
      
      
      
      
      
          
    public boolean ModifierProduit( Produit c) {
        
       String url = Statics.BASE_URL + "updateProduit?idProd=" + c.getIdProd()+"&nomProd=" + c.getNomProd()+"&description=" + c.getDescription()+"&nomPart=" + c.getNomPart();

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
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Produit> getStat() {
        String url = Statics.BASE_URL + "produit_mobile_stat";

 
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               Coachs = parseStat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return Coachs;
    }

    
    
                  
    
    
    
    public ArrayList<Produit> parseStat(String jsonText) {
        
        try {
            Coachs = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {           
          
             
            String LibC = obj.get("nomProd").toString();         
            int  quantite =  (int) Float.parseFloat(obj.get("prix").toString());
           
                
            
     
            
            Produit ab = new Produit();
            ab.setPrix((int)quantite);
            ab.setNomProd(LibC);
            Coachs.add(ab);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Coachs;
    }
    
    
    
    
    
    
    
      
}
