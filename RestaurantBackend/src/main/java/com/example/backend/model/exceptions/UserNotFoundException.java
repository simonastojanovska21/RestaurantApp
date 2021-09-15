package com.example.backend.model.exceptions;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException() {
        super("Username not found");
    }
}
