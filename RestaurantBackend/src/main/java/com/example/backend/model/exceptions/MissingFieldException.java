package com.example.backend.model.exceptions;

public class MissingFieldException extends Throwable{

    public MissingFieldException(){
        super("All fields in the form are mandatory");
    }
}
