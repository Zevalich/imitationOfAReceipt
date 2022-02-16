package com.zevalich.service;

import com.zevalich.entity.DiscountCard;
import com.zevalich.entity.Product;
import com.zevalich.entity.Receipt;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ReceiptHandler {
    private static Receipt receipt;


    public static Receipt getReceipt() {
        return receipt;
    }

    public static Receipt generateReceipt(){
        return receipt = new Receipt(DataHandler.getListOfProductsForTheReceipt(),
                setDiscountCard(), setDiscount());
    }

    private static DiscountCard setDiscountCard(){
        DiscountCard discountCard;
        if(DataHandler.isDiscountCard()){
            discountCard = DataHandler.getDiscountCard();
        }else {
            discountCard = null;
        }
        return discountCard;
    }

    private static int setDiscount(){
        DiscountCard discountCard;
        int discount;
        if(DataHandler.isDiscountCard()){
            discountCard = DataHandler.getDiscountCard();
            discount = discountCard.getDiscount();
            return discount;
        }else {
            return 0;
        }
    }

    public static double getPrice(Product product){
        return product.getPrice();
    }

    public static int countProductQuantity(Product product){
        List<Product> products = receipt.getProductsList();
        int quantity = 0;
        for (Product prod : products) {
            if (prod.equals(product)){
                quantity++;
            }
        }
        return quantity;
    }

    public static double salePrice(Product product){
        double price = product.getPrice();
        if(product.getSpecialOffer() && countProductQuantity(product) >= 5){
            return  price = price - (price*10/100);
        }else {
            return price;
        }
    }

    public static double sale(){
        return totalPrice() - totalPriceWithDiscount();
    }

    public static double priceOneItemByQuantity(Product product){
        return salePrice(product) * countProductQuantity(product);
    }

    public static double totalPrice(){
        Set<Product> productSet = new LinkedHashSet<>(receipt.getProductsList());
        double totalPrice = 0;
        for (Product product : productSet) {
            totalPrice = totalPrice + (salePrice(product) * salePrice(product));
        }
        return totalPrice;
    }

    public static double totalPriceWithDiscount(){
        double totalPrice = totalPrice();
        double saleTotalPrice;
        if(receipt.getDiscountCard() != null){
            saleTotalPrice = totalPrice - (totalPrice * receipt.getDiscount() / 100);
            return  saleTotalPrice;
        }else {
            return totalPrice;
        }
    }

}
