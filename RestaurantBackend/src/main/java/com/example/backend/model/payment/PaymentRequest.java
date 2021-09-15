package com.example.backend.model.payment;

import com.example.backend.model.enumerations.Currency;
import lombok.Data;

@Data
public class PaymentRequest {

    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private Token token;
    private Long orderId;
    private String username;

}
