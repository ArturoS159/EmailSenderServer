package files;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
    public static void send(String recipient,String title, String content){

        final String username = "grupamocnych@gmail.com";
        final String password = "GrupaMocnych2019";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipient));
            message.setSubject(title);
            message.setText(content);
            Transport.send(message);

        } catch (MessagingException e) {
             throw new RuntimeException(e);
        }
    }
}
