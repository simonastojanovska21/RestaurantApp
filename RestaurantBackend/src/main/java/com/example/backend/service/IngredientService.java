package com.example.backend.service;

import com.example.backend.model.Ingredient;
import com.example.backend.model.dto.IngredientDto;
import com.example.backend.model.exceptions.EmptyDataException;
import com.example.backend.model.exceptions.IngredientNotFound;
import com.example.backend.model.exceptions.IngredientWithNameExists;
import com.example.backend.model.exceptions.MissingFieldException;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    Optional<Ingredient> addNewIngredient(IngredientDto ingredientDto) throws MissingFieldException, EmptyDataException, IngredientWithNameExists;
    List<Ingredient> getAllIngredients();
    Optional<Ingredient> getIngredientById(Long id) throws IngredientNotFound;
    Optional<Ingredient> updateIngredient(Long id,IngredientDto ingredientDto) throws IngredientNotFound, MissingFieldException, EmptyDataException, IngredientWithNameExists;
    boolean deleteIngredient(Long id) throws IngredientNotFound;
}
