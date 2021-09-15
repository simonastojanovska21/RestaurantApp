package com.example.backend.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryDto {
    String address;
    Long orderId;
}
