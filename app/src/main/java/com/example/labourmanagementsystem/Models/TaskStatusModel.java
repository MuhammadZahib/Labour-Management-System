package com.example.labourmanagementsystem.Models;

public class TaskStatusModel {

    String CustomerAddress;
    String CustomerDescription;
    String CustomerId;
    String CustomerName;
    String CustomerPhone;
    String CustomerPrice;
    String Date;
    String Skill;
    String Status;
    String Time;

    public TaskStatusModel() {
    }

    public TaskStatusModel(String customerAddress, String customerDescription, String customerId, String customerName, String customerPhone, String customerPrice, String date, String skill, String status, String time) {
        CustomerAddress = customerAddress;
        CustomerDescription = customerDescription;
        CustomerId = customerId;
        CustomerName = customerName;
        CustomerPhone = customerPhone;
        CustomerPrice = customerPrice;
        Date = date;
        Skill = skill;
        Status = status;
        Time = time;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerDescription() {
        return CustomerDescription;
    }

    public void setCustomerDescription(String customerDescription) {
        CustomerDescription = customerDescription;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
