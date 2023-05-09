
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Reservation;
import com.mycompany.utilis.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceReservation {
    
    //singleton 
    public static ServiceReservation instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReservation getInstance() {
        if(instance == null )
            instance = new ServiceReservation();
        return instance ;
    }
    
    
    
    public ServiceReservation() {
        req = new ConnectionRequest();
        
    }
    
    
    
    
    //affichage


    public ArrayList<Reservation>affichageReservations() {
        ArrayList<Reservation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"listReservationsJson";
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
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());                         
                        String nomuser = obj.get("iduser").toString();                        
                        String nomevent = obj.get("idEvent").toString();                        
                        float nbPlaces = Float.parseFloat(obj.get("nbp").toString());                
                        Reservation r = new Reservation((int)id,nomuser,(int)nbPlaces,nomevent);
                        //insert data into ArrayList result
                        result.add(r);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
     public Boolean Ajout(Reservation f) {
        String url = "http://127.0.0.1:8000/addReservationJSON?idEvent="+f.getIdEvent() +"&idUser="+f.getIdUser() + "&nbPlaces="+f.getNbPlaces();
       System.out.println(url);
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // ArrayList<Freelancer> tasks = parseFreelancer(new String(req.getResponseData()));
             //  EmailSender.sendEmail("Nouvelle réservation ajoutée", "Une nouvelle réservation a été ajoutée.");

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    String rep= new String(req.getResponseData());

        return true;
    }
      public Boolean Modif(Reservation f) {
        String url = "http://127.0.0.1:8000/updateReservationJSON?idEvent="+f.getIdEvent() +"&idUser="+f.getIdUser() + "&nbPlaces="+f.getNbPlaces();
       System.out.println(url);
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
       

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    String rep= new String(req.getResponseData());

        return true;
    }
    
    public boolean deleteReservation(int numRes) {
    String url = Statics.BASE_URL + "deleteReservationJSON/" + numRes;
       
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
//
//    
////  public void genererPdf(ArrayList<Reservation> reservations) {
////    try {
////        PDDocument document = new PDDocument();
////        PDPage page = new PDPage();
////        document.addPage(page);
////        PDPageContentStream contentStream = new PDPageContentStream(document, page);
////        contentStream.beginText();
////        contentStream.newLineAtOffset(100, 700);
////        contentStream.showText("Liste des réservations :");
////        contentStream.endText();
////        contentStream.beginText();
////        contentStream.newLineAtOffset(100, 650);
////        for (Reservation r : reservations) {
////            contentStream.showText("Réservation n° " + r.getNumRes()+ " :");
////            contentStream.newLine();
////            contentStream.showText("Utilisateur : " + r.getIdUser());
////            contentStream.newLine();
////            contentStream.showText("Evénement : " + r.getIdEvent());
////            contentStream.newLine();
////            contentStream.showText("Nombre de places : " + r.getNbPlaces());
////            contentStream.newLine();
////            contentStream.newLine();
////        }
////        contentStream.endText();
////        contentStream.close();
////        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
////        document.save(outputStream);
////        document.close();
////        byte[] pdfBytes = outputStream.toByteArray();
////        // Enregistrer le PDF dans la base de données ou l'envoyer par email, etc.
////    } catch (IOException e) {
////        e.printStackTrace();
////    }
////}
//  /*public void genererPDF(List<Reservation> reservations) throws IOException {
//    PDDocument document = new PDDocument();
//    PDPage page = new PDPage(PDRectangle.A4);
//    document.addPage(page);
//    PDPageContentStream contentStream = new PDPageContentStream(document, page);
//
//    contentStream.beginText();
//    contentStream.newLineAtOffset(50, 700);
//    contentStream.showText("Liste des réservations :");
//    contentStream.endText();
//
//    int y = 670;
//    for (Reservation reservation : reservations) {
//        contentStream.beginText();
//        contentStream.newLineAtOffset(50, y);
//        contentStream.showText("Nom d'utilisateur : " + reservation.getIdUser());
//        contentStream.newLineAtOffset(0, -20);
//        contentStream.showText("Nom d'événement : " + reservation.getIdEvent());
//        contentStream.newLineAtOffset(0, -20);
//        contentStream.showText("Nombre de places : " + reservation.getNbPlaces());
//        contentStream.endText();
//
//        y -= 60;
//    }
//
//    contentStream.close();
//
//    File file = new File("reservations.pdf");
//
//    
//    
//
//    
//}*/
}