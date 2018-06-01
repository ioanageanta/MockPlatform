package com.webstudent.platform.notifications;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;

public class Utils {
    public static void sendPost(NotificationRequest notificationRequest) {
        ObjectMapper mapper = new ObjectMapper();
        StringEntity entity = null;
        try {
            entity = new StringEntity(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationRequest),
                    ContentType.APPLICATION_JSON);
            System.out.println(entity.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("https://fcm.googleapis.com/fcm/send");
        request.setEntity(entity);
        request.setHeader(HttpHeaders.AUTHORIZATION, "key=AAAAZcARxsI:APA91bEtgT_O5G7qpiNIIN6kJIzVcbvRN2msbKHK2DUtQSnY3ZffB6uMaSW7irwCO1rauPkW_oxexLs7lIhBI0daUT8F50ZVxGmfwHSvunDVpKEnoYN79g3_fmo3hftotaeinCwAqWsy");

        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
            System.out.println(response.getEntity().getContent().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMail(Integer grade) {
        final String username = "ioanageanta94@gmail.com";
        final String password = "*Bisou11";

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
            message.setFrom(new InternetAddress("ioanageanta94@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("geantaioana13@stud.ase.ro"));
            message.setSubject("Parcusul tau din acest semestru");
            message.setText("Draga Ioana Geanta,"
                    + "\n\n Poti vedea mai jos notele tale din acest semestru. Cu media pe care o ai acum, te clasezi pe pozitia 30 din 150."
                    + "\n\n Programarea dispozitivelor mobile - 9"
                    + "\n\n Managementul proiectelor informatice - 10"
                    + "\n\n Management financiar corporatist - 10"
                    + "\n\n Sisteme integrate – logistică II - 7"
                    + "\n\n Managementul cunoştinţelor - 10"
                    + "\n\n Analiza multidimensională a datelor - 7");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
