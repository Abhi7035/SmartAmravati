package com.example.ash.smartamravati.activity.user.dashboard.Complaints;


import java.util.Date;

public class Complaint {

    public String subject;
    public String name;
    public String mobile;
    public String image_thumb;
    public String complaint_Details;
    public Date timestamp;

    public Complaint(){}

    public Complaint(String subject, String name, String mobile, String image_url, String complaintDesc, Date timestamp) {
        this.subject = subject;
        this.name = name;
        this.mobile = mobile;
        this.image_thumb = image_thumb;
        this.complaint_Details = complaint_Details;
        this.timestamp = timestamp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }

    public String getComplaint_Details() {
        return complaint_Details;
    }

    public void setComplaint_Details(String complaint_Details) {
        this.complaint_Details = complaint_Details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }





}
