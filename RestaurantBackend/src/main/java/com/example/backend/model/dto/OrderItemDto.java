package com.example.backend.model.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    Integer quantity;
    Long mealId;
    String username;
}
