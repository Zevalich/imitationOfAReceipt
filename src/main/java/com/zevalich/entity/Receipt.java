package com.zevalich.entity;

import com.zevalich.service.DataHandler;
import com.zevalich.service.FormatterHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Receipt {
    private  List<Product> productsList;
    private  DiscountCard discountCard;
    private int discount;

    public Receipt(List<Product> productsList, DiscountCard discountCard, int discount) {
        this.productsList = productsList;
        this.discountCard = discountCard;
        this.discount = discount;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public int getDiscount() {
        return discount;
    }

    public void printToConsole(String[] args){
        DataHandler.getDataFromCommandLine(args);
        FormatterHandler.showReceipt();
    }

    public void writeToFile(){
        String data = FormatterHandler.receiptToPrint();
        OutputStream os = null;
        try{
            os = new FileOutputStream(new File("src/main/resources/receipt.txt"));
            os.write(data.getBytes(StandardCharsets.UTF_8),0,data.length());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                os.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
