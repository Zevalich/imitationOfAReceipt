package com.zevalich.entity;

public class Product {
    private int id;
    private String name;
    private double price;
    private boolean isSpecialOffer;

    public Product(int id, String name, double price, boolean isSpecialOffer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isSpecialOffer = isSpecialOffer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Boolean getSpecialOffer() {
        return isSpecialOffer;
    }
}
