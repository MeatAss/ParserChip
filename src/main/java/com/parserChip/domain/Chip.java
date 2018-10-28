package com.parserChip.domain;

public class Chip {
    private String name;
    private String address;
    private String cost;

    public Chip() {
    }

    public Chip(String name, String address, String cost) {
        this.name = name;
        this.address = address;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
