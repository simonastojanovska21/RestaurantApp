package com.example.backend.web;

import com.example.backend.model.Delivery;
import com.example.backend.model.dto.DeliveryDto;
import com.example.backend.model.exceptions.DeliveryNotFound;
import com.example.backend.model.exceptions.EmptyDataException;
import com.example.backend.model.exceptions.MissingFieldException;
import com.example.backend.model.exceptions.OrderNotFound;
import com.example.backend.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RequestMapping("/api/delivery")
public class DeliveryRestController {

    private final DeliveryService deliveryService;

    @PostMapping("/add")
    public ResponseEntity<Delivery>createNewDelivery(@RequestBody DeliveryDto deliveryDto)
    {
        try {
            return this.deliveryService.createNewDelivery(deliveryDto)
                    .map(delivery -> ResponseEntity.ok().body(delivery))
                    .orElseGet(()->ResponseEntity.notFound().build());
        }
        catch (OrderNotFound | EmptyDataException | MissingFieldException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getInfoForDelivery(@PathVariable Long id)
    {
        try {
            return this.deliveryService.getInfoForDelivery(id)
                    .map(delivery -> ResponseEntity.ok().body(delivery))
                    .orElseGet(()->ResponseEntity.notFound().build());
        }
        catch (DeliveryNotFound e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
    @GetMapping("/all")
    public List<Delivery> getAllDeliveries()
    {
        return this.deliveryService.getAllDeliveries();
    }
    @GetMapping("/day/{date}")
    public List<Delivery> getDeliveriesForDay(@PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime date)
    {
        return this.deliveryService.getDeliveriesForDay(date);
    }
    @GetMapping("/remaining")
    public List<Delivery> getRemainingDeliveriesForToday()
    {
        return this.deliveryService.getRemainingDeliveriesForToday();
    }
    @GetMapping("/finish/{id}")
    public ResponseEntity<Delivery> finishedDelivery(@PathVariable Long id)
    {
        try {
            return this.deliveryService.finishedDelivery(id)
                    .map(delivery -> ResponseEntity.ok().body(delivery))
                    .orElseGet(()->ResponseEntity.notFound().build());
        }
        catch (DeliveryNotFound e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
}
