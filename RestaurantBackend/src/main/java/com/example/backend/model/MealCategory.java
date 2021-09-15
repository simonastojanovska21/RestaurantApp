package com.example.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class MealCategory {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String imageUrl;

    public MealCategory (String name, String imageUrl)
    {
        this.name=name;
        this.imageUrl=imageUrl;
    }
}
