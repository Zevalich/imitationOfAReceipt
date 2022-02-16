package com.zevalich.service;

import com.zevalich.entity.DiscountCard;
import com.zevalich.entity.Product;
import com.zevalich.exception_handling.NoSuchCardException;
import com.zevalich.exception_handling.NoSuchIdException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private final static List<Product> productsDataList = new ArrayList<>();
    private final static List<DiscountCard> discountCardsDataList = new ArrayList<>();
    private final static List<Product> listOfProductsForTheReceipt = new ArrayList<>();
    private static DiscountCard discountCard;
    private static final File productsFile = new File("src\\main\\resources\\products.txt");
    private static final File discountCardsFile = new File("src\\main\\resources\\discountcard.txt");


    public static List<Product> getListOfProductsForTheReceipt() {
        return listOfProductsForTheReceipt;
    }

    public static DiscountCard getDiscountCard() {
        return discountCard;
    }

    public static void generateData(){
        generateProductsDataList();
        generateDiscountCardsList();
    }

    public static void getDataFromCommandLine(String[] args){
        for (String arg : args) {
            String[] idQuantity;
            idQuantity = arg.split("-");
            try {
                int id = Integer.parseInt(idQuantity[0]);
                int quantity = Integer.parseInt(idQuantity[1]);
                for (int i = 0; i < quantity; i++) {
                    try {
                        addProductById(id);
                    }catch (NoSuchIdException e){
                        e.getExceptionMessage();
                    }
                }
            }catch (NumberFormatException e){
                String cardNumber = idQuantity[1];
                try {
                    addDiscountCard(cardNumber);
                }catch (NoSuchCardException exception){
                    exception.getExceptionMessage();
                }
            }
        }

    }

    private static void addDiscountCard(String cardNumber) throws NoSuchCardException {
        for (DiscountCard card : discountCardsDataList) {
            if(card.getNumber().equals(cardNumber)){
                discountCard = card;
            }
        }
    }

    private static void addProductById(int id) throws NoSuchIdException {
        Product foundProduct;
        for (Product product : productsDataList) {
            if (product.getId() == id) {
                foundProduct = product;
                listOfProductsForTheReceipt.add(foundProduct);
            }
        }

    }

    private static void generateProductsDataList(){
        try(BufferedReader reader = new BufferedReader(new FileReader(productsFile))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] dataStrings = splitString(line);
                productsDataList.add(new Product(Integer.parseInt(dataStrings[0]),
                        dataStrings[1], Double.parseDouble(dataStrings[2]),
                        Boolean.parseBoolean(dataStrings[3])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateDiscountCardsList(){
        try(BufferedReader reader = new BufferedReader(new FileReader(discountCardsFile))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] dataStrings =splitString(line);
                discountCardsDataList.add(new DiscountCard(dataStrings[0],
                        Integer.parseInt(dataStrings[1])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] splitString(String line){
        return line.split(" ");
    }

    public static boolean isDiscountCard(){
        return discountCard != null;
    }

}
