package com.pepper.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IdOverrideException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public IdOverrideException(String message){
        super(message);
    }
}
