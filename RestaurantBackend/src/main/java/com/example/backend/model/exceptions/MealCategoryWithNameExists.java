package com.example.backend.model.exceptions;

public class MealCategoryWithNameExists extends Throwable{
    public MealCategoryWithNameExists(){
        super("Meal category with name already exists");
    }
}
