package com.pepper.backend.exception;

public class IdOverrideException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public IdOverrideException(String message){
        super(message);
    }
}
