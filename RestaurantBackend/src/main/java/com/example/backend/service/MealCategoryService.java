package com.example.backend.service;

import com.example.backend.model.MealCategory;
import com.example.backend.model.dto.MealCategoryDto;
import com.example.backend.model.exceptions.EmptyDataException;
import com.example.backend.model.exceptions.MealCategoryNotFound;
import com.example.backend.model.exceptions.MealCategoryWithNameExists;
import com.example.backend.model.exceptions.MissingFieldException;

import java.util.List;
import java.util.Optional;

public interface MealCategoryService {
    List<MealCategory> findAllMealCategories();
    Optional<MealCategory> addNewMealCategory(MealCategoryDto mealCategoryDto) throws MissingFieldException, EmptyDataException, MealCategoryWithNameExists;
    Optional<MealCategory> updateMealCategory(Long mealCategoryId, MealCategoryDto mealCategoryDto) throws MealCategoryNotFound, MissingFieldException, EmptyDataException;
    Optional<MealCategory> getMealCategory(Long mealCategoryId) throws MealCategoryNotFound;
    boolean deleteMealCategory(Long mealCategoryId) throws MealCategoryNotFound;
}
