package com.example.backend.model.exceptions;

public class MealCategoryNotFound extends Throwable {
    public MealCategoryNotFound()
    {
        super("Meal category with id is not found");
    }
}
