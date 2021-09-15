package com.example.backend.model.exceptions;

public class MealNotFound extends Throwable {
    public MealNotFound()
    {
        super("Meal with id is not found");
    }
}
