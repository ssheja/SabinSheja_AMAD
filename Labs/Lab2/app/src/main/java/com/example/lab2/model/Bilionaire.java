package com.example.lab2.model;

public class Bilionaire {

    private int rank;
    private String names;
    private String country;
    private String netWorth;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(String netWorth) {
        this.netWorth = netWorth;
    }

    public Bilionaire(int rank, String names, String country, String netWorth) {
        this.rank = rank;
        this.names = names;
        this.country = country;
        this.netWorth = netWorth;
    }
}
