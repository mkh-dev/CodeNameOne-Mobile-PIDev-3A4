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
import com.mycomany.entities.Evenement;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author youss
 */
public class Service_Event {
        
    public ArrayList<Evenement> Coachs;
    public static Service_Event instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Event() {
        req = new ConnectionRequest();
    }

     
    public static Service_Event getInstance() {
        if (instance == null) {
            instance = new Service_Event();
        }
        return instance;
    }
    
    
    
    
    
     public ArrayList<Evenement> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Evenement coach = new Evenement();
                
                
                

               float idEvent = Float.parseFloat(obj.get("idEvent").toString());
               coach.setIdEvent((int) idEvent);
               
             
                float budgetEvent = Float.parseFloat(obj.get("budgetEvent").toString());
               coach.setBudgetEvent((int) budgetEvent);
               
               
       
               
                
               float prix = Float.parseFloat(obj.get("prix").toString());
               coach.setPrix((float) prix);
               
                
               
                
               float capacite = Float.parseFloat(obj.get("capacite").toString());
               coach.setCapacite((int) capacite);
               
                
               
                
                
        
               coach.setNomEvent(obj.get("nomEvent").toString());
               
               
               coach.setDescriptionEvent(obj.get("descriptionEvent").toString());
               
               coach.setDateDebutEvent(obj.get("dateDebutEvent").toString());

                 coach.setDateFinEvent(obj.get("dateFinEvent").toString());
                 
                 

                 coach.setLieuEvent(obj.get("lieuEvent").toString());
                 
                 
                 coach.setImage(obj.get("image").toString());
                 

     


                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<Evenement> findAll() {
        String url = Statics.BASE_URL + "evenement_mobile";
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
    
    
    
    
    
    
    
    
    
    
    
      
          public void AddEvent(Evenement c) {
        String url = Statics.BASE_URL + "newevent_mobile/"+ c.getNomEvent()+ "/" + c.getDescriptionEvent()+ "/" +c.getDateDebutEvent()+ "/" + c.getDateFinEvent()+ "/" +c.getLieuEvent()+ "/" + c.getBudgetEvent()+ "/" +c.getImage()+ "/" + c.getPrix()+ "/" +c.getCapacite(); //création de l'URL

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
    
    
    
    
    
    
    
      public boolean deleteEvent(int idEvent) {
        String url = Statics.BASE_URL + "SupprimerEvent?idEvent=" + idEvent;
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
    
      
      
      
      
      
      
      
      
          
    public boolean ModifierEvent( Evenement c) {
        
       String url = Statics.BASE_URL + "updateEvent?idEvent=" + c.getIdEvent()+"&nomEvent=" + c.getNomEvent()+"&descriptionEvent=" + c.getDescriptionEvent()+"&lieuEvent=" + c.getLieuEvent();

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
