package com.example.backend.service.impl;

import com.example.backend.model.Ingredient;
import com.example.backend.model.Meal;
import com.example.backend.model.MealCategory;
import com.example.backend.model.dto.MealDto;
import com.example.backend.model.exceptions.*;
import com.example.backend.repository.IngredientRepository;
import com.example.backend.repository.MealCategoryRepository;
import com.example.backend.repository.MealRepository;
import com.example.backend.service.MealService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final IngredientRepository ingredientRepository;
    private final MealCategoryRepository mealCategoryRepository;

    @Override
    public Optional<Meal> addNewMealToMenu(MealDto mealDto) throws MealCategoryNotFound, MissingFieldException, EmptyDataException, MealWithNameExists {
        if(mealDto.getName()==null || mealDto.getDescription()==null || mealDto.getMealCategory()==null || mealDto.getIngredientsForMeal()==null
        || mealDto.getPrice()==null || mealDto.getImageUrl()==null)
            throw new MissingFieldException();
        if(mealDto.getName().isEmpty() || mealDto.getDescription().isEmpty()  || mealDto.getMealCategory()==0
                || mealDto.getIngredientsForMeal().isEmpty() || mealDto.getPrice()==0 || mealDto.getImageUrl().isEmpty() )
            throw new EmptyDataException();
        if(this.mealRepository.findMealByName(mealDto.getName()).isPresent())
            throw new MealWithNameExists();
        List<Ingredient> ingredients=this.ingredientRepository.findAllById(mealDto.getIngredientsForMeal());
        MealCategory mealCategory=this.mealCategoryRepository.findById(mealDto.getMealCategory()).orElseThrow(MealCategoryNotFound::new);
        Meal meal=new Meal(mealDto.getName(),mealDto.getDescription(),mealDto.getPrice(),mealCategory,mealDto.getImageUrl(),ingredients);
        return Optional.of(this.mealRepository.save(meal));
    }

    @Override
    public Optional<Meal> getDetailsForMeal(Long id) throws MealNotFound {
        Optional<Meal> meal= this.mealRepository.findById(id);
        if(meal.isEmpty())
            throw new MealNotFound();
        return meal;
    }

    @Override
    public Optional<Meal> updateMeal(Long id, MealDto mealDto) throws MealCategoryNotFound, MealNotFound, MissingFieldException, EmptyDataException {
        if(mealDto.getName()==null || mealDto.getDescription()==null || mealDto.getMealCategory()==null || mealDto.getIngredientsForMeal()==null
                || mealDto.getPrice()==null || mealDto.getImageUrl()==null)
            throw new MissingFieldException();
        if(mealDto.getName().isEmpty() || mealDto.getDescription().isEmpty()  || mealDto.getMealCategory()==0
                || mealDto.getIngredientsForMeal().isEmpty() || mealDto.getPrice()==0 || mealDto.getImageUrl().isEmpty() )
            throw new EmptyDataException();
        List<Ingredient> ingredients=this.ingredientRepository.findAllById(mealDto.getIngredientsForMeal());
        MealCategory mealCategory=this.mealCategoryRepository.findById(mealDto.getMealCategory()).orElseThrow(MealCategoryNotFound::new);
        Meal meal=this.getDetailsForMeal(id).orElseThrow(MealNotFound::new);
        meal.setName(mealDto.getName());
        meal.setDescription(mealDto.getDescription());
        meal.setPrice(mealDto.getPrice());
        meal.setMealCategory(mealCategory);
        meal.setImageUrl(mealDto.getImageUrl());
        meal.setIngredientsForMeal(ingredients);
        return Optional.of(this.mealRepository.save(meal));
    }

    @Override
    public List<Meal> getAllMeals() {
        return this.mealRepository.findAll();
    }


    @Override
    public List<Meal> findAllMealsFromMealCategory(Long mealCategoryId) throws MealCategoryNotFound {
        MealCategory mealCategory=this.mealCategoryRepository.findById(mealCategoryId).orElseThrow(MealCategoryNotFound::new);
        return this.mealRepository.findAllByMealCategory(mealCategory);
    }

    @Override
    public boolean deleteMeal(Long mealId) throws MealNotFound {
        Meal meal=this.getDetailsForMeal(mealId).orElseThrow(MealNotFound::new);
        this.mealRepository.delete(meal);
        return this.mealRepository.findById(mealId).isEmpty();
    }

    @Override
    public int getNumberOfMealsFromCategory(Long mealCategoryId) throws MealCategoryNotFound {
        return this.findAllMealsFromMealCategory(mealCategoryId).size();
    }
}
