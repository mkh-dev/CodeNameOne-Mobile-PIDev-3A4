/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;




import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reservation;
import com.mycompany.services.ServiceReservation;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author ASUS
 */
public class ReservationHome extends Form {

    Form current;

 public ReservationHome(Resources res) {
    current = this;

    setTitle("Liste des Reservations");
    setLayout(BoxLayout.y());
    
    
    

    
    
    
    Button reserver= new Button();
       reserver.addActionListener(e -> {
        try {
            new AjouterResForm().show();
        } catch (IOException ex) {
           // Logger.getLogger(ReservationHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    });

    ServiceReservation sv = new ServiceReservation();
    ArrayList<Reservation> lv = sv.affichageReservations();
    Container list = new Container(BoxLayout.y());

    list.setScrollableY(true);
    for (Reservation lvs : lv) {
        MultiButton sp = new MultiButton(lvs.toString());
        sp.setTextLine1( " Num  " + lvs.getNumRes());
        sp.setTextLine2("User : " + lvs.getIdUser());
        
        // Create a new container for the reservation
        Container reservationContainer = new Container(new BorderLayout());
        reservationContainer.add(BorderLayout.CENTER, sp);
        
//    Button modifier = new Button("Modifier");
//    reservationContainer.add(BorderLayout.EAST, modifier);
//       reserver.addActionListener(e -> {
//        try {
//            ModifResForm.numres=lvs.getNumRes();
//            new ModifResForm().show();
//        } catch (IOException ex) {
//           // Logger.getLogger(ReservationHome.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    });
    // Add a button to the container
    Button button = new Button("Supprimer");
    reservationContainer.add(BorderLayout.WEST, button);
/*
    Button pdf = new Button();
       pdf.addActionListener(e -> {
        try {
            sv.genererPDF(lv);
        } catch (IOException ex) {
           // Logger.getLogger(ReservationHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    });*/
    
  /*  Container cn1 = new Container(BoxLayout.y());
cn1.add(pdf);*/

    // Add a listener to the button
    button.addActionListener(e -> {
        if (Dialog.show("Confirmation", "Voulez-vous supprimer cette réservation ?", "Oui", "Non")) {
            // Call the deleteReservation method
            boolean result = ServiceReservation.getInstance().deleteReservation(lvs.getNumRes());
            
            if (result) {
                // Remove the reservation from the list
                list.removeComponent(reservationContainer);
                list.animateLayout(150);
            } else {
                Dialog.show("Erreur", "Erreur lors de la suppression de la réservation", "OK", null);
            }
        }
    });
    
        
        list.add(reservationContainer);
        
    }
Container cn = new Container(BoxLayout.y());
cn.add(reserver);

    this.add(list);
    this.add(cn);


    
    

    
}

 


}
