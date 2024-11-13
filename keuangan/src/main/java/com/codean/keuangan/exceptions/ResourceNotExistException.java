package com.codean.keuangan.exceptions;

public class ResourceNotExistException extends Exception{

    private String message;

    public ResourceNotExistException(String message){
        super(message);
    }
}
