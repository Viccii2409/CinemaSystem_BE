package com.springboot.CinemaSystem.exception;

public class DataProcessingException extends RuntimeException{
    public DataProcessingException(String message){
        super(message);
    }
}
