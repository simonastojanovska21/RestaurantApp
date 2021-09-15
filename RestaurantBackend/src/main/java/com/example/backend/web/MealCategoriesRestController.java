package com.example.backend.web;

import com.example.backend.model.MealCategory;
import com.example.backend.model.dto.MealCategoryDto;
import com.example.backend.model.exceptions.EmptyDataException;
import com.example.backend.model.exceptions.MealCategoryNotFound;
import com.example.backend.model.exceptions.MealCategoryWithNameExists;
import com.example.backend.model.exceptions.MissingFieldException;
import com.example.backend.service.MealCategoryService;
import com.example.backend.service.MealService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/mealCategories")
@AllArgsConstructor
public class MealCategoriesRestController {

    private final MealCategoryService mealCategoryService;
    private final MealService mealService;

    @GetMapping
    public List<MealCategoryDto> getAll()
    {
        return this.mealCategoryService.findAllMealCategories()
                .stream()
                .map(each-> {
                    try {
                        return new MealCategoryDto(each.getId(),each.getName(),each.getImageUrl(),this.mealService.getNumberOfMealsFromCategory(each.getId()));
                    } catch (MealCategoryNotFound e) {
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
                    }
                })
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MealCategory> findById(@PathVariable Long id) {
        try {
            return this.mealCategoryService.getMealCategory(id)
                    .map(mealCategory -> ResponseEntity.ok().body(mealCategory))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (MealCategoryNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<MealCategory> addNewMealCategory(@RequestBody MealCategoryDto mealCategoryDto) {
        try {
            return this.mealCategoryService.addNewMealCategory(mealCategoryDto)
                    .map(mealCategory -> ResponseEntity.ok().body(mealCategory))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (MissingFieldException | EmptyDataException | MealCategoryWithNameExists e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<MealCategory> save(@PathVariable Long id, @RequestBody MealCategoryDto mealCategoryDto) {
        try {
            return this.mealCategoryService.updateMealCategory(id,mealCategoryDto)
                    .map(product -> ResponseEntity.ok().body(product))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (MealCategoryNotFound | MissingFieldException | EmptyDataException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        boolean result;
        try {
            result = this.mealCategoryService.deleteMealCategory(id);
        } catch (MealCategoryNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
        if(result)
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
