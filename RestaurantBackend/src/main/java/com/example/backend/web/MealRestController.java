package com.example.backend.web;

import com.example.backend.model.Meal;
import com.example.backend.model.dto.MealDto;
import com.example.backend.model.exceptions.*;
import com.example.backend.service.MealCategoryService;
import com.example.backend.service.MealService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/meals")
@AllArgsConstructor
public class MealRestController {

    private final MealService mealService;

    @PostMapping("/add")
    public ResponseEntity<Meal> addNewMeal(@RequestBody MealDto mealDto)
    {
        try {
            return this.mealService.addNewMealToMenu(mealDto)
                    .map(meal -> ResponseEntity.ok().body(meal))
                    .orElseGet(()->ResponseEntity.notFound().build());
        } catch (MealCategoryNotFound | MissingFieldException | EmptyDataException | MealWithNameExists e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable Long id)
    {
        try {
            return this.mealService.getDetailsForMeal(id)
                    .map(meal -> ResponseEntity.ok().body(meal))
                    .orElseGet(()->ResponseEntity.notFound().build());
        } catch (MealNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Meal> updateMealFromMenu(@PathVariable Long id, @RequestBody MealDto mealDto)
    {
        try {
            return this.mealService.updateMeal(id,mealDto)
                    .map(meal -> ResponseEntity.ok().body(meal))
                    .orElseGet(()->ResponseEntity.notFound().build());
        } catch (MealCategoryNotFound | MealNotFound | MissingFieldException | EmptyDataException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @GetMapping("/all")
    public List<Meal> getAllMeals()
    {
        return this.mealService.getAllMeals();
    }

    @GetMapping("/mealCategory/{mealCategoryId}")
    public List<Meal> getAllMealsFromCategory(@PathVariable Long mealCategoryId)
    {
        try {
            return this.mealService.findAllMealsFromMealCategory(mealCategoryId);
        } catch (MealCategoryNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMealFromMenu(@PathVariable Long id)
    {
        boolean result;
        try {
            result = this.mealService.deleteMeal(id);
        } catch (MealNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
        if(result)
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();

    }

}
