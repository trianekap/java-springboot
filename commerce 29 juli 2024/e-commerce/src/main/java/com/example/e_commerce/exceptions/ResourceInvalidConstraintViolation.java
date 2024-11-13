package com.example.e_commerce.exceptions;

public class ResourceInvalidConstraintViolation extends Exception{
    private String message;
    public ResourceInvalidConstraintViolation (String message){
        super(message);
    }
}
