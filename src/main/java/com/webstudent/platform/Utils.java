package com.webstudent.platform;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class Utils {
    public static void sendPost() {
        String payload = "{\n" +
                "  \"to\":\n" +
                "    \"fCqX5JaHZsQ:APA91bG9e0B2Q3zWBJdY5G5bgzIlWjgWntDu2nynPp-v4g-oY-KR83alk6rWjiNYLdcvvW0M5OmfVK3BKlGf66hGV3gTLNATOk4X9FO5zdIC4-AX76QFP2lAUfcJ5GOXAiWocUBF0zt_\",\n" +
                "  \"data\": {\n" +
                "    \"title\": \"I'd tell you a chemistry joke\",\n" +
                "    \"message\": \"but I know I wouldn't get a reaction\",\n" +
                "    \"image-url\":\n" +
                "      \"https://docs.centroida.co/wp-content/uploads/2017/05/notification.png\"\n" +
                "  }\n" +
                "}";
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

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
