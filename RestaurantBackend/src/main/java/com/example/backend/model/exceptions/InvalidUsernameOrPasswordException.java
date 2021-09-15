package com.example.backend.model.exceptions;

public class InvalidUsernameOrPasswordException extends Throwable{
    public InvalidUsernameOrPasswordException (String message)
    {
        super(message);
    }
}
