package com.codean.CoffeShop.exception;

public class NonExistenceResourceException extends Exception{
    private String message;

    public NonExistenceResourceException(String message){
        super(message);
    }
}
