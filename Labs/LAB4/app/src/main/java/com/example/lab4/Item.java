package com.example.lab4;

public class Item {
    private String name;
    private String phoneNumber;

    public Item(){
        // Default constructor
    }

    public Item(String name, String phone) {
        this.name = name;
        this.phoneNumber = phone;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phoneNumber;
    }
}