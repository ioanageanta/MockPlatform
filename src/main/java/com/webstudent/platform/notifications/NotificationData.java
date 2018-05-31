package com.webstudent.platform.notifications;

public class NotificationData {
    private String title;
    private String message;
    private String imageURL="";

    public NotificationData() {
        title = "You got a new grade";
    }

    public NotificationData(String title, String message, String imageURL) {
        this.title = title;
        this.message = message;
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
