///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.gui;
//
//import static com.codename1.push.PushContent.setTitle;
//import com.codename1.ui.Button;
//import static com.codename1.ui.Component.CENTER;
//import com.codename1.ui.Container;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.TextField;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.layouts.FlowLayout;
//import com.codename1.ui.util.Resources;
//import com.mycompany.entities.Reservation;
//import com.mycompany.services.ServiceReservation;
//import java.io.IOException;
//
///**
// *
// * @author ouesl
// */
//public class ModifResForm extends Form {
//    public static int numres;
//    public ModifResForm () throws IOException{
//          Form previous = new AjouterResForm();
//    setTitle("Modification Reservation");
//    setLayout(new FlowLayout(CENTER, CENTER));
//        
//    Reservation o = ServiceReservation.getInstance().getResBYid(numres);System.out.println(o);
//         TextField nbPlaces = new TextField("","Nombre Places");
//
//     
//    Button btnLogin = new Button("Modifier");
//    Container cn = new Container(BoxLayout.y());
//       
//    btnLogin.addActionListener(e->{
//        ServiceReservation su= new ServiceReservation();
//       // o.setId(ListOffreForm.offreid);
//     o.setNbPlaces( Integer.parseInt(nbPlaces.getText()));
//       o.setIdEvent("3");
//       o.setIdUser("59");
//        boolean mod = su.Modif(o); 
//        if (mod) {
//            Dialog.show("Success", "Res modifié ! ", "OK", null);
//        } else {
//            Dialog.show("Error", "Erreur lors de la modification de la res", "OK", null);
//        }
//    });
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
//        cn.addAll(nbPlaces, btnLogin);
//    add(cn);        
//        
//        Button btnBack = new Button("Retour");
//btnBack.addActionListener(e -> {
//    Resources theme = null;
//    new ReservationHome(theme).show(); // assuming ReservationHome is the name of your form
//});
//cn.add(btnBack);
//
//    }
//
////    private void setLayout(FlowLayout flowLayout) {
////     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }
////
////    private void add(Container cn) {
////       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }
////
////    void show() {
////        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }
//    }
//
