package com.example.springboot.Exception;

public class ObjectNotFoundException extends RuntimeException {

    private String message;

    public ObjectNotFoundException(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
}
