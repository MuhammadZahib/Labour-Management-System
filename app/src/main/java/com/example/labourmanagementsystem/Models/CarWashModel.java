package com.example.labourmanagementsystem.Models;

import java.io.Serializable;

public class CarWashModel implements Serializable {

    String UserID;
    String City;
    String CompanyName;
    String Contact;
    String Description;
    String Image;

    public CarWashModel() {

    }

    public CarWashModel(String UserID,String city, String companyName, String contact, String description, String image) {
        UserID=UserID;
        City = city;
        CompanyName = companyName;
        Contact = contact;
        Description = description;
        Image = image;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
