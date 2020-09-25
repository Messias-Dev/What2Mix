package com.what2mix.exception;

public class DataInsufficientException extends  Exception {

    private static final long serialVersionUID = 1L;

    public DataInsufficientException(String message){
        super(message);
    }

}
