package com.codean.topup.exceptions;

public class ResourceAlreadyException extends Exception{

    private String message;

    public ResourceAlreadyException (String message){
        super(message);
    }
}
