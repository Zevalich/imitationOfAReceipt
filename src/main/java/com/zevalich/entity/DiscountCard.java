package com.zevalich.entity;

public class DiscountCard {
    private String number;
    private int discount;

    public DiscountCard(String number, int discount) {
        this.number = number;
        this.discount = discount;
    }

    public String getNumber() {
        return number;
    }

    public int getDiscount() {
        return discount;
    }

}
