package com.example.backend.model.dto;

import lombok.Data;

@Data
public class ReviewDto {
    Integer stars;
    String description;
    String username;
}
