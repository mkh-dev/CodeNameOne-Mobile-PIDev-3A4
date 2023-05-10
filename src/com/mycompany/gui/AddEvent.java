/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Evenement;
import com.mycompany.services.Service_Event;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author youss
 */
public class AddEvent extends BaseForm{
      String file ;
      String file2 ;
      Resources theme;
    
     public AddEvent(Form previous) throws IOException {
      super("Add Event", BoxLayout.y());
      theme = UIManager.initFirstTheme("/themeCoHeal");
 
Label AJOUT = new Label("ADD New Event");
 this.add(AJOUT);

            
  TextField nom = new TextField("", "nom event", 20, TextArea.TEXT_CURSOR);
  
  TextField description = new TextField("", "description event", 20, TextArea.TEXT_CURSOR);
  TextField lieuEvent = new TextField("", "lieu Event", 20, TextArea.TEXT_CURSOR);
  
  TextField budgetEvent = new TextField("", "budgetEvent", 20, TextArea.TEXT_CURSOR);
    TextField prix = new TextField("", "prix", 20, TextArea.TEXT_CURSOR);
    TextField capacite = new TextField("", "capacite", 20, TextArea.TEXT_CURSOR);

  


              Picker datedebutevent = new Picker();
              Picker datefinevent = new Picker();


       
   
      Button save = new Button("Ajouter");
      
     
        
        this.add("Nom : ").add(nom);
        this.add("description : ").add(description);
        this.add("lieuEvent : ").add(lieuEvent);
        this.add("budgetEvent : ").add(budgetEvent);
       this.add("prix : ").add(prix);
              this.add("capacite : ").add(capacite);

           this.add("date debut event : ").add(datedebutevent);
           this.add("date fin event : ").add(datefinevent);

               
                                
  
        this.add(save);
        
        
        
        
        
        
        

    
 
             
        
    
        save.addActionListener(l
                                -> {

                            if (nom.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide de Nom Event ", "OK", null);

                            }  else if (description.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide description  ", "OK", null);
                                
                            }else if (lieuEvent.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide lieuEvent  ", "OK", null);
                                
                            }
                            else if (budgetEvent.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Taper budgetEvent ", "OK", null);
                                
                            }
                            
                            else if (prix.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Taper prix ", "OK", null);
                                
                            } 
                            else if (capacite.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Taper capacite ", "OK", null);
                                
                            } 
                            
                            
                            
                 else {
                                
                           
                                try {
                                  
                                   DateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
                                    Date ddebut = datedebutevent.getDate();
                                    String dated = dd.format(ddebut);
                                    
                                    
                                    
                                    DateFormat dd2 = new SimpleDateFormat("yyyy-MM-dd");
                                    Date ddebut2 = datefinevent.getDate();
                                    String dated2 = dd2.format(ddebut2);
                                    
                                    
                            
                                    
                                    Evenement c = new Evenement();
                                    
                                    
                                    c.setNomEvent(nom.getText());
                                    
                                     c.setDescriptionEvent(description.getText());
                                     
                                     c.setImage("image.jpeg");
                                     
                                      c.setLieuEvent(lieuEvent.getText());
                                      c.setDateDebutEvent(dated);
                                      c.setDateFinEvent(dated2);

                                     
                                    c.setPrix(Float.valueOf(prix.getText()));
                                   c.setCapacite(Integer.valueOf(capacite.getText()));
                                   c.setBudgetEvent(Integer.valueOf(budgetEvent.getText()));

                                   // c.setCategorie(Integer.valueOf(1));


                         
                                    
           System.out.println("forms.addEvet.addItem()"+c);
           new Service_Event().AddEvent(c);
           com.codename1.ui.Dialog.show("Ajouter Evenement", "Ajouter Evenement aves success ", "OK", null);
                                        
                                        
                                 
                                        
  /////////////////////////////////////   Notification     /////////////////////
  
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Evenement  "+c.getNomEvent()+"  ajoute avec succes");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("hallllllllllllllllllllllllllllllo");
////////////////////////////////////////////
                                        
                                } catch (Exception ex) {
                                       System.out.println("hekllllo");
                                }

                            }

                        }
                        );
        
        
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
      
        
        
        
        
        
        
        
        
           
           this.getToolbar().addCommandToLeftSideMenu("Back", null, ev -> {
               try {
                  new EventForm(this).showBack();
               } catch (Exception ex) {
            
               }
               
               
               
        });
        
        
           
           
           
           
           






           
        
 this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
        
        this.show();

             
     
     
                            

      
     
     }
     
     
     
     private void addButton(Image img, Resources res) {
         
         
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        add(cnt);  
        
		}
     
     
     
     
     private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overLay = new Label("", "ImageOverLay");

     
    }

   
}
