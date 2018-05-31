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
}
