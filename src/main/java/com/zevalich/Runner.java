package com.zevalich;

import com.zevalich.entity.Receipt;
import com.zevalich.service.DataHandler;
import com.zevalich.service.ReceiptHandler;

public class Runner {
    public static void main(String[] args) {
        DataHandler.generateData();
        Receipt receipt = ReceiptHandler.generateReceipt();
        receipt.printToConsole(args);
        receipt.writeToFile();
    }
}
