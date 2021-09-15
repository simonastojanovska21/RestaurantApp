package com.example.backend.model.exceptions;

public class UserCanNotHave2ActiveOrders extends Throwable{
    public UserCanNotHave2ActiveOrders()
    {
        super("User can not have more tha 2 active orders");
    }
}
