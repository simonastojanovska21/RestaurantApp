package com.example.backend.service.impl;

import com.example.backend.model.Delivery;
import com.example.backend.model.Order;
import com.example.backend.model.dto.DeliveryDto;
import com.example.backend.model.enumerations.OrderStatus;
import com.example.backend.model.exceptions.DeliveryNotFound;
import com.example.backend.model.exceptions.EmptyDataException;
import com.example.backend.model.exceptions.MissingFieldException;
import com.example.backend.model.exceptions.OrderNotFound;
import com.example.backend.repository.DeliveryRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;

    @Override
    public Optional<Delivery> createNewDelivery(DeliveryDto deliveryDto) throws OrderNotFound, EmptyDataException, MissingFieldException {
        if(deliveryDto.getAddress()==null || deliveryDto.getOrderId()==null)
            throw new MissingFieldException();
        if (deliveryDto.getAddress().isEmpty() || deliveryDto.getOrderId()==0)
            throw new EmptyDataException();
        Order order=this.orderRepository.findById(deliveryDto.getOrderId()).orElseThrow(OrderNotFound::new);
        order.setOrderStatus(OrderStatus.DELIVERING);
        this.orderRepository.save(order);
        Delivery delivery=new Delivery(deliveryDto.getAddress(),LocalDateTime.now().plusHours(1), order);
        return Optional.of(this.deliveryRepository.save(delivery));
    }

    @Override
    public Optional<Delivery> getInfoForDelivery(Long deliveryId) throws DeliveryNotFound {
        Optional<Delivery> delivery= this.deliveryRepository.findById(deliveryId);
        if(delivery.isEmpty())
            throw new DeliveryNotFound();
        return delivery;
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return this.deliveryRepository.findAll();
    }

    @Override
    public List<Delivery> getDeliveriesForDay(LocalDateTime day) {
        return this.deliveryRepository.findAll()
                .stream()
                .filter(each->each.getTimeForDelivery().getDayOfYear()==day.getDayOfYear())
                .collect(Collectors.toList());
    }

    @Override
    public List<Delivery> getRemainingDeliveriesForToday() {
        return this.deliveryRepository.findAll()
                .stream()
                .filter(each->each.getTimeForDelivery().isAfter(LocalDateTime.now())
                       // && each.getTimeForDelivery().getDayOfYear()==LocalDateTime.now().getDayOfYear()
                        && each.getOrderForDelivery().getOrderStatus()!=OrderStatus.RECEIVED)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Delivery> finishedDelivery(Long deliveryId) throws DeliveryNotFound {
        Delivery delivery=this.deliveryRepository.findById(deliveryId).orElseThrow(DeliveryNotFound::new);
        Order order=delivery.getOrderForDelivery();
        order.setOrderStatus(OrderStatus.RECEIVED);
        this.orderRepository.save(order);
        return Optional.of(delivery);
    }
}
