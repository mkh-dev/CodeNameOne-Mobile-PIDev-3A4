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
import com.mycomany.entities.Reclamation;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceReclamation {
    
    //singleton 
    public static ServiceReclamation instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReclamation getInstance() {
        if(instance == null )
            instance = new ServiceReclamation();
        return instance ;
    }
    
    
    
    public ServiceReclamation() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutReclamation(Reclamation reclamation) {
        
        String url =Statics.BASE_URL+"addReclamation?prenom="+reclamation.getPrenom()+"&nom="+reclamation.getNom()+"&email="+reclamation.getEmail()+"&message="+reclamation.getMessage(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Reclamation>affichageReclamations() {
        ArrayList<Reclamation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"displayReclamations";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Reclamation re = new Reclamation();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String prenom = obj.get("prenom").toString();
                        
                        String nom = obj.get("nom").toString();
                        String email = obj.get("email").toString();
                        String message = obj.get("message").toString();
                        
                        re.setId((int)id);
                        re.setPrenom(prenom);
                        re.setNom(nom);
                         re.setEmail(email);
                         re.setMessage(message);
                        
                        
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
    //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Reclamation DetailRecalamation( int id , Reclamation reclamation) {
        
        String url = Statics.BASE_URL+"detailReclamation"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                reclamation.setPrenom(obj.get("prenom").toString());
                reclamation.setNom(obj.get("nom").toString());
                reclamation.setEmail(obj.get("email").toString());
                reclamation.setMessage(obj.get("message").toString());
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return reclamation;
        
        
    }
    
    
    //Delete 
    public boolean deleteReclamation(int id ) {
        String url = Statics.BASE_URL +"deleteReclamation?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
    //Update 
    public boolean modifierReclamation(Reclamation reclamation) {
        String url = Statics.BASE_URL + "updateReclamation?id=" + reclamation.getId() + "&prenom=" + reclamation.getPrenom() + "&nom=" + reclamation.getNom() + "&email=" + reclamation.getEmail() + "&message=" + reclamation.getMessage();

        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    

    
}