package com.zevalich.exception_handling;

public class NoSuchIdException extends Exception{
    public void getExceptionMessage(){
        System.err.println("There is no such id");
        this.printStackTrace();
        System.exit(0);
    }
}
