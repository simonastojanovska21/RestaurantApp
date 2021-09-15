package com.example.backend.model.exceptions;

public class MealWithNameExists extends Throwable{
    public MealWithNameExists(){
        super("Meal with name already exists");
    }
}
