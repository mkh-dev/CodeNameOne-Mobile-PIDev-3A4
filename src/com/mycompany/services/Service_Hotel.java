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
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Service_Hotel {
        
    public ArrayList<Hotel> Coachs;
    public static Service_Hotel instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Hotel() {
        req = new ConnectionRequest();
    }

     
    public static Service_Hotel getInstance() {
        if (instance == null) {
            instance = new Service_Hotel();
        }
        return instance;
    }
    
    
    
    
    
     public ArrayList<Hotel> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Hotel coach = new Hotel();
                
                
                

               float id = Float.parseFloat(obj.get("id").toString());
               coach.setId((int) id);
               
             
                float nbrEtoile = Float.parseFloat(obj.get("nbrEtoile").toString());
               coach.setNbrEtoile((int) nbrEtoile);
               
               
                  float prixlpd = Float.parseFloat(obj.get("prixlpd").toString());
               coach.setPrixlpd((int) prixlpd);
               
                
               
                  
                  float prixpc = Float.parseFloat(obj.get("prixpc").toString());
               coach.setPrixpc((int) prixpc);
               
               
                    
                  float prixdp = Float.parseFloat(obj.get("prixdp").toString());
               coach.setPrixdp((int) prixdp);
               
                
                
        
               coach.setNom(obj.get("nom").toString());
               
               
               coach.setLieux(obj.get("lieux").toString());
               
               coach.setImage(obj.get("image").toString());

                 coach.setEmail(obj.get("email").toString());


     


                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<Hotel> findAll() {
        String url = Statics.BASE_URL + "hotel_mobile";
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
    
    
    
    
    
    
    
    
    
    
    
          public void AddHotel(Hotel c) {
        String url = Statics.BASE_URL + "newhotel_mobile/"+ c.getNom()+ "/" + c.getNbrEtoile()+ "/" +c.getLieux()+ "/" + c.getImage()+ "/" +c.getEmail()+ "/" + c.getPrixlpd()+ "/" +c.getPrixpc()+ "/" + c.getPrixdp(); //création de l'URL

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
    
    
    
    
    
    
    
    
    
    public boolean deleteHotel(int id) {
        String url = Statics.BASE_URL + "SupprimerHotel?id=" + id;
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
    
    
    
    
    
    
    
    
    
    
    public boolean ModifierHotel( Hotel c) {
        
       String url = Statics.BASE_URL + "updateHotel?id=" + c.getId()+"&prixlpd=" + c.getPrixlpd()+"&prixpc=" + c.getPrixpc()+"&prixdp=" + c.getPrixdp();

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

    
    
    
    
    
    public ArrayList<Hotel> getStat() {
        String url = Statics.BASE_URL + "hotel_mobile_stat";

 
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

    
    
                  
    
    
    
    public ArrayList<Hotel> parseStat(String jsonText) {
        
        try {
            Coachs = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {           
          
             
            String LibC = obj.get("nom").toString();         
            int  quantite =  (int) Float.parseFloat(obj.get("nbrEtoile").toString());
           
                
            
     
            
            Hotel ab = new Hotel();
            ab.setNbrEtoile((int)quantite);
            ab.setNom(LibC);
            Coachs.add(ab);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Coachs;
    }
    
    
    
    
    
    
    
}
