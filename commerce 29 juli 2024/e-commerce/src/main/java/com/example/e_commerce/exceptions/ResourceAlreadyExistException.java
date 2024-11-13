package com.example.e_commerce.exceptions;

public class ResourceAlreadyExistException extends Exception {
    private String message;

    public ResourceAlreadyExistException(String message){
        super(message);
    }
}
