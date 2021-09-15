package com.example.backend.repository;

import com.example.backend.model.Meal;
import com.example.backend.model.MealCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findAllByMealCategory(MealCategory mealCategory);
    Optional<Meal> findMealByName(String name);
}
