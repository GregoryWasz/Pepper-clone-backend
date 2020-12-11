package com.pepper.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthenticatedException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public UnauthenticatedException(String message){
        super(message);
    }
}
