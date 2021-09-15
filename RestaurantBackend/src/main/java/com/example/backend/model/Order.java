package com.example.backend.model;

import com.example.backend.model.enumerations.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderedOn;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    private User orderedByUser;

    public Order(LocalDateTime orderedOn, OrderStatus orderStatus, User orderedByUser)
    {
        this.orderedOn=orderedOn;
        this.orderStatus=orderStatus;
        this.orderedByUser=orderedByUser;
    }
}
