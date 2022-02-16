package com.zevalich.exception_handling;

public class NoSuchCardException extends Exception{
    public void getExceptionMessage(){
        System.err.println("There is no such card number");
        this.printStackTrace();
        System.exit(0);
    }
}
