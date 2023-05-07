/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.utilis.Statics;
import com.mycompany.gui.AjoutReclamationForm;
import com.mycompany.gui.ListReclamationForm;
import com.mycompany.gui.SessionManager;
import java.util.Map;
import java.util.Vector;
import javafx.scene.control.DatePicker;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class ServiceUtilisateur {
    
    
  //singleton 
    public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiceUtilisateur();
        return instance ;
    }
    
    
    
    public ServiceUtilisateur() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
public void signup(TextField prenom, TextField nom, TextField email, Picker datenaissance, TextField numtel, ComboBox<String> userrole, TextField password, Resources res) {
    // Format de la date de naissance
    String dateNaissanceStr = datenaissance.getText();
    
    String url = Statics.BASE_URL+"users/signup?prenom="+prenom.getText().toString()
                                            +"&nom="+nom.getText().toString()
                                            +"&email="+email.getText().toString()
                                            +"&datenaissance="+dateNaissanceStr
                                            +"&numtel="+numtel.getText()
                                            +"&userrole="+userrole.getSelectedItem().toString()
                                            +"&password="+password.getText().toString();
                                            
    req.setUrl(url);
    
    //Control saisi
    if(prenom.getText().equals("") || nom.getText().equals("") || email.getText().equals("") || datenaissance.getText().equals("") || numtel.getText().equals("") || userrole.getSelectedItem() == null || password.getText().equals("")) {
        Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
        return;
    }

    //hethi wa9t tsir execution ta3 url 
    req.addResponseListener((e)-> {
     
        //njib data ly7atithom fi form 
        byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
        String responseData = new String(data);//ba3dika na5o content 
        
        System.out.println("data ===>"+responseData);
    }
    );
    
    
    //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
    NetworkManager.getInstance().addToQueueAndWait(req);
}



    
    
    //SignIn
    
    public void signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"users/signin?email="+email.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassword(user.get("password").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                
                
                if(user.size() >0 ) // l9a user
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    new AjoutReclamationForm(rs).show();
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    

  //heki 5dmtha taw nhabtha ala description
    public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"users/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }

}