package com.what2mix.exception;

public class UserNotFoundException extends Exception{

    private static final long serialVersionUID = 6L;

    public UserNotFoundException(String message){
        super(message);
    }
}
