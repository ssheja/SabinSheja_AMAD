package com.example.lab1sabinsheja;

public class Students {
    private String firstName;
    private String lastName;
    private String nickName;
    private String phoneNumber;
    private int ratings;

    public Students(String firstName, String lastName, String nickName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.ratings = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public int getRatings() {
        return ratings;
    }
}
