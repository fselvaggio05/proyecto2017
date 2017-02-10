package datos;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import javax.activation.*;

public class ServicioEnvioCorreo {
protected static final String Username = "clinicapasos@gmail.com";
protected static final String PassWord = "proyecto2017";
//private static final String To = "";
//private static final String Subject = "Prueba";
//private static final String Mensage = "Este mensaje es una prueba desde la aplicación de Proyecto Final!";

public void enviarCorreo(String subject, String mensage, String to) {
	//permite enviar un correo con un asunto y mensaje a un remitente o to 
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Username, PassWord);
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(Username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(mensage);

        Transport.send(message);
       System.out.println( "Su mensaje ha sido enviado con éxito.");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}}
