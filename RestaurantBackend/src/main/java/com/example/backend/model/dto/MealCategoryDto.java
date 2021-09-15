package com.example.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MealCategoryDto {
    private Long id;
    private String name;
    private String imageUrl;
    private Integer numberOfMeals;
}
