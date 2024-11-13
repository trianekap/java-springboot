package com.codean.CoffeShop.exception;

public class ResourceNotExistException extends Exception{

    private String message;

    public ResourceNotExistException(String message){
        super(message);
    }
}
