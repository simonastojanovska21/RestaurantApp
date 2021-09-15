package com.example.backend.model.exceptions;

public class IngredientWithNameExists extends Throwable{
    public IngredientWithNameExists(){
        super("Ingredient with name already exists");
    }
}
