package com.example.backend.model.exceptions;

public class PasswordsDoNotMatchException extends Throwable{
    public PasswordsDoNotMatchException(String message){
        super(message);
    }
}
