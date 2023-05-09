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
import com.mycomany.entities.Produit;
import com.mycompany.services.Service_Produit;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author youss
 */
public class AddProduit extends BaseForm{
      String file ;
      String file2 ;
      Resources theme;
    
     public AddProduit(Form previous) throws IOException {
      super("AddProduit", BoxLayout.y());
      theme = UIManager.initFirstTheme("/themeCoHeal");
 
Label AJOUT = new Label("ADD New Produit");
 this.add(AJOUT);

            
  TextField nom = new TextField("", "nom produit", 20, TextArea.TEXT_CURSOR);
  
  TextField description = new TextField("", "description", 20, TextArea.TEXT_CURSOR);
  TextField prix = new TextField("", "prix", 20, TextArea.TEXT_CURSOR);
  
  TextField quantite = new TextField("", "quantite", 20, TextArea.TEXT_CURSOR);
  TextField part = new TextField("", "nom part", 20, TextArea.TEXT_CURSOR);
  

 // TextField categorie = new TextField("", "categorie", 20, TextArea.TEXT_CURSOR);



       
        Button upload = new Button("Upload Image Produit");
        upload.setUIID("vtnvalid");
   
      Button save = new Button("Ajouter");
      
     
        
        this.add("Nom : ").add(nom);
        this.add("description : ").add(description);
        this.add("prix : ").add(prix);
        this.add("quantite : ").add(quantite);
        this.add("part : ").add(part);
       // this.add("categorie : ").add(categorie);

               
                                
  
        this.add(upload);
        this.add(save);
        
        
        
        
        
        
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost:8080/MobileProject/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    file=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    com.codename1.ui.Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                                        
            }
        });
        

    
 
             
        
    
        save.addActionListener(l
                                -> {

                            if (nom.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide de Nom ", "OK", null);

                            }  else if (description.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide description  ", "OK", null);
                                
                            }else if (prix.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide prix  ", "OK", null);
                                
                            }
                            else if (quantite.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Taper quantite ", "OK", null);
                                
                            }
                             else if (part.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Taper Nom partenaire ", "OK", null);
                                
                            } 
                            
                            
                            
                 else {
                                
                           
                                try {
                                  
                          
                                    
                                    Produit c = new Produit();
                                    
                                    
                                    c.setNomProd(nom.getText());
                                    
                                     c.setDescription(description.getText());
                                     c.setNomPart(part.getText());
                                     

                                     
                                    c.setQuantite(Integer.valueOf(quantite.getText()));
                                     c.setPrix(Integer.valueOf(prix.getText()));
                                     
                                  c.setNbLikes(0);
                                  c.setNbDislikes(0);


                                   // c.setCategorie(Integer.valueOf(1));

                                      c.setImage(file);

                         
                                    
           System.out.println("forms.addEvet.addItem()"+c);
           new Service_Produit().AddProduit(c);
           com.codename1.ui.Dialog.show("Ajouter Produit", "Ajout Produit aves success ", "OK", null);
                                        
                                        
                                 
                                        
  /////////////////////////////////////   Notification     /////////////////////
  
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Produit  "+c.getNomProd()+"  ajoute avec succes");
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
                   new ProduitForm(this).showBack();
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
