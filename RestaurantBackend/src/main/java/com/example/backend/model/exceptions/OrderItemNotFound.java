package com.example.backend.model.exceptions;

public class OrderItemNotFound extends Throwable{
    public OrderItemNotFound()
    {
        super("Order item with id not found");
    }
}
