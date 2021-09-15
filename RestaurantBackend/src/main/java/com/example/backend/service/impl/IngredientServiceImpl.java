package com.example.backend.service.impl;

import com.example.backend.model.Ingredient;
import com.example.backend.model.dto.IngredientDto;
import com.example.backend.model.exceptions.EmptyDataException;
import com.example.backend.model.exceptions.IngredientNotFound;
import com.example.backend.model.exceptions.IngredientWithNameExists;
import com.example.backend.model.exceptions.MissingFieldException;
import com.example.backend.repository.IngredientRepository;
import com.example.backend.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Optional<Ingredient> addNewIngredient(IngredientDto ingredientDto) throws MissingFieldException, EmptyDataException, IngredientWithNameExists {
        if(ingredientDto.getName()==null || ingredientDto.getQuantity()==null)
            throw new MissingFieldException();
        if (ingredientDto.getName().isEmpty() || ingredientDto.getQuantity()==0)
            throw new EmptyDataException();
        if(this.ingredientRepository.findIngredientByName(ingredientDto.getName()).isPresent())
            throw new IngredientWithNameExists();
        Ingredient ingredient=new Ingredient(ingredientDto.getName(),ingredientDto.getQuantity());
        return Optional.of(this.ingredientRepository.save(ingredient));
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return this.ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> getIngredientById(Long id) throws IngredientNotFound {
        Optional<Ingredient> ingredient= this.ingredientRepository.findById(id);
        if (ingredient.isEmpty())
            throw new IngredientNotFound();
        return ingredient;
    }

    @Override
    public Optional<Ingredient> updateIngredient(Long id,IngredientDto ingredientDto) throws IngredientNotFound, MissingFieldException, EmptyDataException {
        if(ingredientDto.getName()==null || ingredientDto.getQuantity()==null)
            throw new MissingFieldException();
        if (ingredientDto.getName().isEmpty() || ingredientDto.getQuantity()==0)
            throw new EmptyDataException();
        Ingredient ingredient=this.ingredientRepository.findById(id).orElseThrow(IngredientNotFound::new);
        ingredient.setName(ingredientDto.getName());
        ingredient.setQuantity(ingredientDto.getQuantity());
        return Optional.of(this.ingredientRepository.save(ingredient));
    }

    @Override
    public boolean deleteIngredient(Long id) throws IngredientNotFound {
        this.getIngredientById(id);
        this.ingredientRepository.deleteById(id);
        return this.ingredientRepository.findById(id).isEmpty();
    }
}
