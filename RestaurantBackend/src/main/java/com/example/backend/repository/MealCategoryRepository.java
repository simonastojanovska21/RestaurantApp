package com.example.backend.repository;

import com.example.backend.model.MealCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MealCategoryRepository extends JpaRepository<MealCategory,Long> {
    Optional<MealCategory> findMealCategoryByName(String name);
}
