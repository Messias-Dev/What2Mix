package com.what2mix.exception;

public class InsufficientDataException extends Exception{
    private static final long serialVersionUID = 5L;

    public InsufficientDataException(String message){
        super(message);
    }
}
