package com.example.backend.model.exceptions;

public class IngredientNotFound extends Throwable {
    public IngredientNotFound()
    {
        super("Ingredient with id is not found");
    }
}
