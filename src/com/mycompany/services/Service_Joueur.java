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
import com.mycomany.entities.Hotel;
import com.mycomany.entities.Joueur;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Service_Joueur {
        
    public ArrayList<Joueur> Coachs;
    public static Service_Joueur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Joueur() {
        req = new ConnectionRequest();
    }

     
    public static Service_Joueur getInstance() {
        if (instance == null) {
            instance = new Service_Joueur();
        }
        return instance;
    }
    
    
    
    
    
     public ArrayList<Joueur> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Joueur coach = new Joueur();
                
                
                

               float idjoueur = Float.parseFloat(obj.get("idjoueur").toString());
               coach.setIdjoueur((int) idjoueur);
               
             
                float age = Float.parseFloat(obj.get("age").toString());
               coach.setAge((int) age);
               
               
       
               
                
               /* float categorie = Float.parseFloat(obj.get("categorie").toString());
               coach.setCategorie((int) categorie);
               */
                
                
        
               coach.setNomjoueur(obj.get("nomjoueur").toString());
               
               
               coach.setPrenomjoueur(obj.get("prenomjoueur").toString());
               
               coach.setDatedenaissance(obj.get("datedenaissance").toString());

                 coach.setSexe(obj.get("sexe").toString());

                 coach.setVille(obj.get("ville").toString());
                 
                 
                 coach.setImgjoueur(obj.get("imgjoueur").toString());
                 

     


                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<Joueur> findAll() {
        String url = Statics.BASE_URL + "Joueur_mboile";
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
    
    
    
    
    
    
    
    
    
    
    
      
          public void AddJoueur(Joueur c) {
        String url = Statics.BASE_URL + "newjoueur_mobile/"+ c.getNomjoueur()+ "/" + c.getPrenomjoueur()+ "/" +c.getDatedenaissance()+ "/" + c.getAge()+ "/" +c.getSexe()+ "/" + c.getVille()+ "/" +c.getImgjoueur(); //création de l'URL

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
    
    
    
    
    
    
    
      public boolean deleteJoueur(int idjoueur) {
        String url = Statics.BASE_URL + "SupprimerJoueur?idjoueur=" + idjoueur;
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
    
      
      
      
      
      
      
      
      
          
    public boolean ModifierJoueur( Joueur c) {
        
       String url = Statics.BASE_URL + "updateJoueur?idjoueur=" + c.getIdjoueur()+"&nomjoueur=" + c.getNomjoueur()+"&prenomjoueur=" + c.getPrenomjoueur()+"&ville=" + c.getVille();

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
