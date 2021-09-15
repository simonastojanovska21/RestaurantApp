package com.example.backend.model.exceptions;

public class EmptyDataException extends Throwable {
    public EmptyDataException() {
        super("The information sent have empty field");
    }
}
