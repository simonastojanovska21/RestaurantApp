package com.example.backend.model.exceptions;

public class DeliveryNotFound extends Throwable {
    public DeliveryNotFound (){
        super("Delivery with id is not found");
    }
}
