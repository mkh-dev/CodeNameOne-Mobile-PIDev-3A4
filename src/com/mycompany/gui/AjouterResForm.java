/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reservation;
import com.mycompany.services.ServiceReservation;
import java.io.IOException;

/**
 *
 * @author ouesl
 */
public class AjouterResForm extends Form {
      public AjouterResForm()  throws IOException {
    


        setTitle("Ajouter Reservation ");
        setLayout(new FlowLayout(CENTER, CENTER));
      //  System.out.println(CurrentUser.getInstance().getId());
         //      Freelancer fr = ServiceFreelancer.getInstance().getFreelancer(CurrentUser.getInstance().getId());

       // TextField tfUsername = new TextField(fr.getUsername());
       Reservation fr= new Reservation();
        TextField nbPlaces = new TextField("","Nombre Places");
       
              //  TextField cat = new TextField(fr.getCategorie());
      
        
        
       // ComboBox<String> cat = new ComboBox();
         
       //       EnumSet.allOf(Categorie.class).stream().forEach(s -> cat.addItem(s.toString()));
        
        //TextField tfPwd = new TextField("", TextField.PASSWORD);
        
        
        
        
        Button btnLogin = new Button("Réserver");
        Container cn = new Container(BoxLayout.y());
       
        btnLogin.addActionListener(e->{
            ServiceReservation su= new ServiceReservation();
             Reservation f=new Reservation();
      
        f.setNbPlaces( Integer.parseInt(nbPlaces.getText()));
       f.setIdEvent("4");
       f.setIdUser("59");
       
        Boolean mod = su.Ajout(f); // Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
             Dialog.show("Success", "Reservation Ajouté ! ", "OK", null);
        });
        
        cn.addAll(nbPlaces, btnLogin);
        add(cn); 
        
        Button btnBack = new Button("Retour");
btnBack.addActionListener(e -> {
    Resources theme = null;
    new ReservationHome(theme).show(); // assuming ReservationHome is the name of your form
});
cn.add(btnBack);

    }


 
}
