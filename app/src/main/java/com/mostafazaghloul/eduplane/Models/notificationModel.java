package com.mostafazaghloul.eduplane.Models;

public class notificationModel {
    private String notificationTitle, notificationTime;
    private int notificationImage;
    public notificationModel(String courseTitle, String courseTime, int notificationImage){
        this.notificationTitle = courseTitle;
        this.notificationTime = courseTime;
        this.notificationImage = notificationImage;
    }

    public int getNotificationImage() {
        return notificationImage;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }
}
