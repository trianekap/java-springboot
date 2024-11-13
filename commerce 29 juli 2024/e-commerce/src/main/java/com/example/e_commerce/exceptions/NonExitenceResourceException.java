package com.example.e_commerce.exceptions;

public class NonExitenceResourceException extends Exception{

    private String message;

    public NonExitenceResourceException(String message){
        super(message);
    }
}
