package com.example.springboot.Exception;

public class UnsuccessfulException extends RuntimeException{
    
    private String message ;
    public UnsuccessfulException(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
