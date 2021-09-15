package com.example.backend.service;

import com.example.backend.model.Meal;
import com.example.backend.model.dto.MealDto;
import com.example.backend.model.exceptions.*;

import java.util.List;
import java.util.Optional;

public interface MealService {
    Optional<Meal> addNewMealToMenu(MealDto mealDto) throws MealCategoryNotFound, MissingFieldException, EmptyDataException, MealWithNameExists;
    Optional<Meal> getDetailsForMeal(Long id) throws MealNotFound;
    Optional<Meal>updateMeal(Long id,MealDto mealDto) throws MealCategoryNotFound, MealNotFound, MissingFieldException, EmptyDataException;
    List<Meal> getAllMeals();
    List<Meal> findAllMealsFromMealCategory(Long mealCategory) throws MealCategoryNotFound;
    boolean deleteMeal(Long mealId) throws MealNotFound;
    int getNumberOfMealsFromCategory(Long mealCategoryId) throws MealCategoryNotFound;
}
