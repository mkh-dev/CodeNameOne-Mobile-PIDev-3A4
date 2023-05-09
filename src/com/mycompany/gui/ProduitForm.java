/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Produit;
import com.mycompany.services.Service_Produit;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author youss
 */
public class ProduitForm extends BaseForm{
      
    
    
    
    Resources theme = UIManager.initFirstTheme("/themeCoHeal");
    public ProduitForm(Form previous)
    {
     
     
           super("Produits",BoxLayout.y());
           
           
       
   
                 this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                
                
             for (Produit c : new Service_Produit().findAll()) {

            this.add(addItem_Coach(c));

        }
               this.revalidate();
            });
        });

        
        ///// recherche///
        this.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : this.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : this.getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    mb.setUIIDLine1("libC");
                    mb.setUIIDLine2("btn");
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);
        
        
     
        
              
          this.getToolbar().addCommandToRightBar("add", null, ev -> {
               try {
               new AddProduit(this).show();
               } catch (Exception ex) {
            
               }
               
               
               
        });
           
           
           
                  
  

    }
    
 
//    
    
    
    
    
    
    
   
    
    
     public Container addItem_Cotch_detail(Produit c) {
  String url = "http://127.0.0.1:8000/uploads/produits/" + c.getImage();
  
  
      
  
  
  
            ImageViewer image_coach;
            Image imge;
            EncodedImage enc;
            enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge = URLImage.createToStorage(enc, url, url);
            
                 image_coach = new ImageViewer(imge);
        
        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        
        Label nom = new Label("Nom  : "+c.getNomProd());          
        Label image = new Label("image : "+String.valueOf(c.getImage()));
        Label prenom = new Label("Description  : "+c.getDescription());          


       
       Label age = new Label("Prix  : "+c.getPrix());   
       
       
       Label sexe = new Label("Quantite  : "+c.getQuantite());    
       
       Label ville = new Label("NB like  : "+c.getNbLikes());       

       Label cat = new Label("NB Dislike  : "+c.getNbDislikes());       
            

      
           
        Button screen = new Button("Screen");
                Button Statistique = new Button("Statistique");


        cn2.add(nom).add(image).add(prenom).add(age).add(sexe).add(ville).add(cat).add(screen).add(Statistique);
        cn1.add(BorderLayout.WEST, cn2);
        
        
        
     
        
        
        
        
          Statistique.addActionListener((x) -> {                        
        new StatProduit().createPieChartForm("Produits", new Service_Produit().getStat(), theme);       
        });
        
        
        
        
       
        screen.addActionListener(e -> {
                   
             Form form = Display.getInstance().getCurrent();
        if (form != null) {
            
            Image screenshot = Image.createImage(form.getWidth(), form.getHeight());
form.revalidate();
form.setVisible(true);
form.paintComponent(screenshot.getGraphics(), true);

String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
} catch(IOException err) {
    Log.e(err);
}
        }  
            
            });
 
      
        return cn1;

    }
     
     
   public MultiButton  addItem_Coach(Produit c) {
     
          MultiButton m = new MultiButton();
          
/////////////////////////////////////   Notification     /////////////////////
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Liste des Produits");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("Hallo");
///////////////////////////////////////////

                   
               

  String url = "http://127.0.0.1:8000/uploads/produits/" + c.getImage();
            ImageViewer image_coach;
            Image imge;
            EncodedImage enc;
            enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge = URLImage.createToStorage(enc, url, url);
                 image_coach = new ImageViewer(imge);
        
        Label nom = new Label("Produit  : "+c.getNomProd());
        Label lieux = new Label("Nom Part  : "+c.getNomPart());

                        
       
       m.setTextLine1(c.getNomProd());
        m.setTextLine2(c.getNomPart());

     //  m.setTextLine2(c.getDateFinContrat());
          
        m.setEmblem(theme.getImage("round.png"));
      
            m.setIcon(imge);
              m.addActionListener(l
                -> {

            Form f2 = new Form("Detail",BoxLayout.y());
            
            f2.add(addItem_Cotch_detail(c));
             f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
                   new ProduitForm(this).showBack();
        });
            f2.show(); });
         
        return m;

    }
         
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
