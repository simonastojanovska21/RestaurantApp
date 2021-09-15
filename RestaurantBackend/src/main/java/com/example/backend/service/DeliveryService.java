package com.example.backend.service;

import com.example.backend.model.Delivery;
import com.example.backend.model.dto.DeliveryDto;
import com.example.backend.model.exceptions.DeliveryNotFound;
import com.example.backend.model.exceptions.EmptyDataException;
import com.example.backend.model.exceptions.MissingFieldException;
import com.example.backend.model.exceptions.OrderNotFound;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DeliveryService {

    Optional<Delivery> createNewDelivery(DeliveryDto deliveryDto) throws OrderNotFound, EmptyDataException, MissingFieldException;
    Optional<Delivery> getInfoForDelivery(Long deliveryId) throws DeliveryNotFound;
    List<Delivery> getAllDeliveries();
    List<Delivery> getDeliveriesForDay(LocalDateTime day);
    List<Delivery> getRemainingDeliveriesForToday();
    Optional<Delivery> finishedDelivery(Long deliveryId) throws DeliveryNotFound;
}
