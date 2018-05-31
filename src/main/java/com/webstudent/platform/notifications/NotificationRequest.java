package com.webstudent.platform.notifications;

public class NotificationRequest {
    private String to;
    private NotificationData data;

    public NotificationRequest() {
        to = "fCqX5JaHZsQ:APA91bG9e0B2Q3zWBJdY5G5bgzIlWjgWntDu2nynPp-v4g-oY-KR83alk6rWjiNYLdcvvW0M5OmfVK3BKlGf66hGV3gTLNATOk4X9FO5zdIC4-AX76QFP2lAUfcJ5GOXAiWocUBF0zt_";
    }

    public NotificationRequest(String to, NotificationData data) {
        this.to = to;
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public NotificationData getData() {
        return data;
    }

    public void setData(NotificationData data) {
        this.data = data;
    }
}
