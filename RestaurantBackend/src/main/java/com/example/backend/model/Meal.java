package com.example.backend.model;

import com.example.backend.model.enumerations.MealType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 500)
    private String description;

    private double price;

    @ManyToOne
    private MealCategory mealCategory;

    private String imageUrl;

    @ManyToMany
    private List<Ingredient> ingredientsForMeal;

    public Meal(String name, String description, double price, MealCategory mealCategory, String imageUrl, List<Ingredient> ingredients)
    {
        this.name=name;
        this.description=description;
        this.price=price;
        this.mealCategory=mealCategory;
        this.imageUrl=imageUrl;
        this.ingredientsForMeal=ingredients;
    }
}
