package com.example.backend.service.impl;

import com.example.backend.model.MealCategory;
import com.example.backend.model.dto.MealCategoryDto;
import com.example.backend.model.exceptions.EmptyDataException;
import com.example.backend.model.exceptions.MealCategoryNotFound;
import com.example.backend.model.exceptions.MealCategoryWithNameExists;
import com.example.backend.model.exceptions.MissingFieldException;
import com.example.backend.repository.MealCategoryRepository;
import com.example.backend.service.MealCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MealCategoryServiceImpl implements MealCategoryService {

    private final MealCategoryRepository mealCategoryRepository;

    @Override
    public List<MealCategory> findAllMealCategories() {
        return this.mealCategoryRepository.findAll();
    }

    @Override
    public Optional<MealCategory> addNewMealCategory(MealCategoryDto mealCategoryDto) throws MissingFieldException, EmptyDataException, MealCategoryWithNameExists {
        if(mealCategoryDto.getName()==null || mealCategoryDto.getImageUrl()==null)
            throw new MissingFieldException();
        if(mealCategoryDto.getName().isEmpty() || mealCategoryDto.getImageUrl().isEmpty() )
            throw new EmptyDataException();
        if(this.mealCategoryRepository.findMealCategoryByName(mealCategoryDto.getName()).isPresent())
            throw new MealCategoryWithNameExists();
        MealCategory mealCategory=new MealCategory(mealCategoryDto.getName(),mealCategoryDto.getImageUrl());
        return Optional.of(this.mealCategoryRepository.save(mealCategory));
    }

    @Override
    @Transactional
    public Optional<MealCategory> updateMealCategory(Long mealCategoryId, MealCategoryDto mealCategoryDto) throws MealCategoryNotFound, MissingFieldException, EmptyDataException {
        if(mealCategoryDto.getName()==null || mealCategoryDto.getImageUrl()==null)
            throw new MissingFieldException();
        if(mealCategoryDto.getName().isEmpty() || mealCategoryDto.getImageUrl().isEmpty() )
            throw new EmptyDataException();
        MealCategory mealCategory=this.getMealCategory(mealCategoryId).orElseThrow(MealCategoryNotFound::new);
        mealCategory.setName(mealCategoryDto.getName());
        mealCategory.setImageUrl(mealCategoryDto.getImageUrl());
        return Optional.of(this.mealCategoryRepository.save(mealCategory));
    }

    @Override
    public Optional<MealCategory> getMealCategory(Long mealCategoryId) throws MealCategoryNotFound {
        Optional<MealCategory>  mealCategory=this.mealCategoryRepository.findById(mealCategoryId);
        if(mealCategory.isEmpty())
            throw new MealCategoryNotFound();
        return mealCategory;
    }

    @Override
    public boolean deleteMealCategory(Long mealCategoryId) throws MealCategoryNotFound {
        this.getMealCategory(mealCategoryId);
        this.mealCategoryRepository.deleteById(mealCategoryId);
        return this.mealCategoryRepository.findById(mealCategoryId).isEmpty();
    }
}
