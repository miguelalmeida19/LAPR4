package eapli.base.customermanagement.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

    public static void sendWelcomeEmail(String toEmail){
        String myEmail = "noreply.isep.lapr4@gmail.com";
        String myPassword = "ehyxisazyzppbwuj";

        SimpleEmail email = new SimpleEmail();

        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(myEmail, myPassword));

        email.setSSLOnConnect(true);

        try {
            email.setFrom(myEmail);
            email.setSubject("Welcome to the App");

            String htmlFileName = "files/index.html";

            MimeBodyPart part = new MimeBodyPart();
            part.attachFile(htmlFileName);
            part.setDisposition(Part.INLINE);

            email.setContent(new MimeMultipart(part));

            email.addTo(toEmail);
            email.send();
            System.out.println("Check your inbox !");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
