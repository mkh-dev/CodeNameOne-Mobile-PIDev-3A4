package com.mycompany.services;

//
//
//
//package com.mycompany.services;
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.util.Properties;
//
//public class EmailSender {
//    
//    private static final String USERNAME = "yosra.oueslati@esprit.tn";
//    private static final String PASSWORD = "223JFT1166";
//    private static final String HOST = "smtp.gmail.com";
//    private static final String PORT = "587";
//    private static final String FROM = "yosra.oueslati@esprit.tn";
//    private static final String TO = "ouesletyyosra@gmail.com";
//
//    public static void sendEmail(String subject, String body) {
//        
//        
//        
//        
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", HOST);
//        props.put("mail.smtp.port", PORT);
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(USERNAME, PASSWORD);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(FROM));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO));
//            message.setSubject(subject);
//            message.setText(body);
//
//            Transport.send(message);
//
//            System.out.println("Email envoyé avec succès !");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
