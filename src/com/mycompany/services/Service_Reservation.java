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
import com.mycomany.entities.HotelReservation;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Service_Reservation {
    
     public ArrayList<HotelReservation> Coachs;
    public static Service_Reservation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Reservation() {
        req = new ConnectionRequest();
    }

     
    public static Service_Reservation getInstance() {
        if (instance == null) {
            instance = new Service_Reservation();
        }
        return instance;
    }
    
    
    
    
    
     public ArrayList<HotelReservation> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                HotelReservation coach = new HotelReservation();
                
                
                

               float id = Float.parseFloat(obj.get("id").toString());
               coach.setId((int) id);
               
             
                float idUser = Float.parseFloat(obj.get("iduser").toString());
               coach.setIduser((int) idUser);
               
               
             
               float idh = Float.parseFloat(obj.get("idh").toString());
               coach.setIdh((int) idh);
               
               
                
               
                   
               float nbrnuite = Float.parseFloat(obj.get("nbrnuite").toString());
               coach.setNbrnuite((int) nbrnuite);
               
               
                
                       
               float prixtot = Float.parseFloat(obj.get("prixtot").toString());
               coach.setPrixtot((int) prixtot);
               
               
               
                
        
               coach.setTypeChambre(obj.get("typeChambre").toString());
               
               
               coach.setTypeReservation(obj.get("typeReservation").toString());
               
               
               
              coach.setDateArrive(obj.get("dateArrive").toString());

              coach.setDateDepart(obj.get("dateDepart").toString());


     


                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<HotelReservation> findAll() {
        String url = Statics.BASE_URL + "reservationhotel_mobile";
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
    
    
    public boolean deleteHotelReservation(int id) {
        String url = Statics.BASE_URL + "SupprimerHotelReservation?id=" + id;
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
    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    
    
    
                  
    
    

    
    
}
