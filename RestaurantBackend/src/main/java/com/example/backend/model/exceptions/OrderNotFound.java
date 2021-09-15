package com.example.backend.model.exceptions;

public class OrderNotFound extends Throwable {
    public OrderNotFound()
    {
        super("Order with id not found");
    }
}
