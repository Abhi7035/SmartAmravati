package com.example.ash.smartamravati.activity;

/**
 * Created by ash on 11-01-2018.
 */

class UserInformation {

    private String tittle;
    private String name;
    private String email;
    private String mobileNo;
    private String address;
    private String pincode;
    private String DoBirth;

    public UserInformation(){

    }

    public UserInformation(String tittle, String name, String email, String mobileNo, String address, String pincode, String DoBirth)
    {
        this.tittle = tittle;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.address = address;
        this.pincode = pincode;
        this.DoBirth = DoBirth ;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode=pincode;
    }
    public String getDoBirth() {
        return DoBirth;
    }

    public void setDoBirth(String DoBirth) {
        this.DoBirth=DoBirth;
    }
}
