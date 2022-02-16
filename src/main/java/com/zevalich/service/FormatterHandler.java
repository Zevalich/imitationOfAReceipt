package com.zevalich.service;

import com.zevalich.entity.Product;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

public class FormatterHandler {
    private static final String HEADER = "CASH RECEIPT";
    private static final String NAME = "SUPERMARKET 123";
    private static final String ADRESS = "12, MILKYWAY Galaxy/Earth";
    private static final String TELEPHONE = "111-11-11";


    private static final String LINE = "---------------------------------";
    private static final String ENDLINE = "---------------------------------\n" +
            "---------------------------------";
    private static final String CASHIER = "CASHIER: №1520";
    private static final String QTY = "QTY";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String PRICE = "PRICE";
    private static final String TOTAL = "TOTAL";
    private static final String TAXABLETOT = "TAXABLE TOT.";
    private static final String VAT = "VAT";

    private static final String taxTot = String.format("%.2f",ReceiptHandler.totalPrice());
    private static final String vat = String.valueOf(ReceiptHandler.getReceipt().getDiscount());
    private static final String total = String.format("%.2f", ReceiptHandler.totalPriceWithDiscount());
    private static final String salePrice = String.format("%.2f",ReceiptHandler.sale());

    private static final String sixSpace = "      ";
    private static final String threeSpace = "   ";
    private static final String endSpace = "                 ";
    private static final String endSpace2 = "                        ";


    public static void showReceipt(){
        System.out.println(addHeader());
        System.out.println(addBody());
        System.out.println(addFooter());
    }

    public static String receiptToPrint(){
        String receipt = addHeader() + "\n" + addBody() + addFooter();
        return receipt;
    }

    private static String addHeader(){
        StringBuilder header = new StringBuilder();
        header.append(centerString(HEADER)).append("\n").append(centerString(NAME)).append("\n")
                        .append(centerString(ADRESS)).append("\n").append(centerString(TELEPHONE)).append("\n")
                .append(LINE).append("\n").append(CASHIER).append("        ").append(formatDate()).append("\n")
                .append("                       ").append(formatTime()).append("\n").append(LINE).append("\n")
                .append(QTY).append(threeSpace).append(DESCRIPTION).append(threeSpace).append(PRICE).append(threeSpace)
                .append(TOTAL);
        return String.valueOf(header);
    }
    private static String addBody(){
        StringBuilder body = new StringBuilder();
        Set<Product> productSet = new LinkedHashSet<>(ReceiptHandler.getReceipt().getProductsList());
        for (Product product : productSet) {
            body.append(ReceiptHandler.countProductQuantity(product)).append(sixSpace).append(product.getName())
                    .append("         ").append(ReceiptHandler.getPrice(product)).append("     ")
                    .append(ReceiptHandler.priceOneItemByQuantity(product)).append("   \n");
        }
        body.append(ENDLINE).append("\n");
        return String.valueOf(body);
    }

    private static String addFooter(){
        StringBuilder footer = new StringBuilder();
        footer.append(TAXABLETOT).append(endSpace).append(taxTot).append("\n")
                .append(VAT).append(vat).append("%").append(endSpace2)
                .append(salePrice).append("\n").append(TOTAL).append(endSpace2)
                .append(total).append("\n");
        return String.valueOf(footer);
    }

    private static String centerString (String s) {
        int width = 32;
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    private static String formatDate(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private static String formatTime(){
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
