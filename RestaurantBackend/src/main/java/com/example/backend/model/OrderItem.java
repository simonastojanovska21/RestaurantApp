package com.example.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    private Order itemInOrder;

    @ManyToOne
    private Meal orderItemForMeal;

    public OrderItem(int quantity, Order itemInOrder, Meal orderItemForMeal)
    {
        this.quantity=quantity;
        this.itemInOrder=itemInOrder;
        this.orderItemForMeal=orderItemForMeal;

    }
}
