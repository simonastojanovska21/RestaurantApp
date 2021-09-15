package com.example.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private LocalDateTime timeForDelivery;

    @OneToOne
    private Order orderForDelivery;

    public Delivery(String address, LocalDateTime timeForDelivery, Order orderForDelivery)
    {
        this.address=address;
        this.timeForDelivery=timeForDelivery;
        this.orderForDelivery=orderForDelivery;
    }
}
