/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;

/**
 *
 * @author Lenovo
 */
public class ModifierReclamationForm extends BaseForm {
    
    Form current;
    public ModifierReclamationForm(Resources res , Reclamation r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField prenom = new TextField(r.getPrenom() , "Prenom" , 20 , TextField.ANY);
        TextField nom = new TextField(r.getNom() , "Nom" , 20 , TextField.ANY);
        TextField email = new TextField(r.getEmail() , "Email" , 20 , TextField.ANY);
        TextField message = new TextField(String.valueOf(r.getMessage()) , "Message" , 20 , TextField.ANY);
 
        
        
        
         
        prenom.setUIID("NewsTopLine");
        nom.setUIID("NewsTopLine");
        email.setUIID("NewsTopLine");
        message.setUIID("NewsTopLine");
        
        prenom.setSingleLineTextArea(true);
        nom.setSingleLineTextArea(true);
        email.setSingleLineTextArea(true);
        message.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setPrenom(prenom.getText());
            r.setNom(nom.getText());
             r.setEmail(email.getText());
           r.setMessage(message.getText());
           
     
       
       //appel fonction modfier reclamation men service
       
       if(ServiceReclamation.getInstance().modifierReclamation(r)) { // if true
           new ListReclamationForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListReclamationForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}
