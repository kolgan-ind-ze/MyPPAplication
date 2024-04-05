package com.example.myppaplication.classes;

import java.io.Serializable;

public class Orders implements Serializable {
    private int id;
    private String name;
    private int price;
    private String size;
    private String metal;
    private String color;
    private byte[] plan;
    private String date;
    private int make;
    private int userId;

    public Orders(String name, int price, String size, String metal, String color, byte[] plan, String date, int make, int userId) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.metal = metal;
        this.color = color;
        this.plan = plan;
        this.date = date;
        this.make = make;
        this.userId = userId;
    }

    public Orders(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public byte[] getPlan() {
        return plan;
    }

    public void setPlan(byte[] plan) {
        this.plan = plan;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMake() {
        return make;
    }

    public void setMake(int make) {
        this.make = make;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
