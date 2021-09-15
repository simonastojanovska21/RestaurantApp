package com.example.backend.web;

import com.example.backend.model.Ingredient;
import com.example.backend.model.dto.IngredientDto;
import com.example.backend.model.exceptions.EmptyDataException;
import com.example.backend.model.exceptions.IngredientNotFound;
import com.example.backend.model.exceptions.IngredientWithNameExists;
import com.example.backend.model.exceptions.MissingFieldException;
import com.example.backend.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/ingredients")
@AllArgsConstructor
public class IngredientRestController {

    private final IngredientService ingredientService;

    @GetMapping
    public List<Ingredient> getAll()
    {
        return this.ingredientService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable Long id) {
        try {
            return this.ingredientService.getIngredientById(id)
                    .map(ingredient -> ResponseEntity.ok().body(ingredient))
                    .orElseGet(()->ResponseEntity.notFound().build());
        }
        catch (IngredientNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<Ingredient> addNewIngredient(@RequestBody IngredientDto ingredientDto) {
        try {
            return this.ingredientService.addNewIngredient(ingredientDto)
                    .map(ingredient -> ResponseEntity.ok().body(ingredient))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (MissingFieldException | EmptyDataException | IngredientWithNameExists e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Ingredient> save(@PathVariable Long id, @RequestBody IngredientDto ingredientDto) {
        try {
            return this.ingredientService.updateIngredient(id,ingredientDto)
                    .map(ingredient -> ResponseEntity.ok().body(ingredient))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
        catch (IngredientNotFound | MissingFieldException | EmptyDataException | IngredientWithNameExists e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        boolean result;
        try {
            result = this.ingredientService.deleteIngredient(id);
        } catch (IngredientNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
        if(result)
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
