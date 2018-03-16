package com.example.ash.smartamravati.activity.admin.dashboard.Notification;


import java.sql.Timestamp;
import java.util.Date;

public class Notification {

    public String tittle1;
    public String image_url;
    public String desc;

    public Notification(String tittle1, String image_url, String desc, String image_thumb, Date timestamp) {
        this.tittle1 = tittle1;
        this.image_url = image_url;
        this.desc = desc;
        this.image_thumb = image_thumb;
        this.timestamp = timestamp;
    }


    public Date timestamp;



    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Notification(){}





    public String getTittle1() {
        return tittle1;
    }

    public void setTittle1(String tittle1) {
        this.tittle1 = tittle1;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }

    public String image_thumb;




}
