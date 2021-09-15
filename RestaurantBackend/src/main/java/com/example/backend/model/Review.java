package com.example.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int stars;

    private String description;

    private LocalDateTime localDateTime;

    @ManyToOne
    private User user;

    public Review(int stars,String description,User user)
    {
        this.stars=stars;
        this.description=description;
        this.localDateTime=LocalDateTime.now();
        this.user=user;
    }
}
