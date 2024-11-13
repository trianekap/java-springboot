package com.codean.CoffeShop.exception;

public class ResourceAlreadyExistException extends Exception{

    private String message;

    public ResourceAlreadyExistException(String message){
        super(message);
    }
}
