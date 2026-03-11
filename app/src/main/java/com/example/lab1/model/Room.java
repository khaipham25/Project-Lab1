package com.example.lab1.model;

import java.io.Serializable;

public class Room implements Serializable {
    private String roomId;
    private String roomName;
    private double price;
    private boolean rented;
    private String tenantName;
    private String phone;

    public Room() {
    }

    public Room(String roomId, String roomName, double price, boolean rented, String tenantName, String phone) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.price = price;
        this.rented = rented;
        this.tenantName = tenantName;
        this.phone = phone;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}