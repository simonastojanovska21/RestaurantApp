package com.example.backend.model.exceptions;

public class InvalidUsernameException extends Throwable{
    public InvalidUsernameException(String message)
    {
        super(message);
    }
}
