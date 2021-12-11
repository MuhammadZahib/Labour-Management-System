package com.example.labourmanagementsystem.Models;

public class InboxModel {

    private String CustomerName;
    private String CustomerId;
    private String Skill;
    private String CustomerAddress;
    private String CustomerPhone;
    private String CustomerPrice;
    private String CustomerDescription;
    private String Time;
    private String Date;

    public InboxModel() {

    }

    public InboxModel(String customerName, String customerId, String skill, String customerAddress, String customerPhone, String customerPrice, String customerDescription, String time, String date) {
        CustomerName = customerName;
        CustomerId = customerId;
        Skill = skill;
        CustomerAddress = customerAddress;
        CustomerPhone = customerPhone;
        CustomerPrice = customerPrice;
        CustomerDescription = customerDescription;
        Time = time;
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return CustomerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        CustomerPhone = customerPhone;
    }

    public String getCustomerPrice() {
        return CustomerPrice;
    }

    public void setCustomerPrice(String customerPrice) {
        CustomerPrice = customerPrice;
    }

    public String getCustomerDescription() {
        return CustomerDescription;
    }

    public void setCustomerDescription(String customerDescription) {
        CustomerDescription = customerDescription;
    }
}
