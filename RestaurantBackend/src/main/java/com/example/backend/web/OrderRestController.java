package com.example.backend.web;

import com.example.backend.model.Meal;
import com.example.backend.model.Order;
import com.example.backend.model.OrderItem;
import com.example.backend.model.dto.OrderItemDto;
import com.example.backend.model.enumerations.OrderStatus;
import com.example.backend.model.exceptions.*;
import com.example.backend.service.OrderService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderRestController {
    private final OrderService orderService;

    @GetMapping("/all")
    public List<Order> getAllOrders()
    {
        return orderService.getAllOrders();
    }
    @GetMapping("/user/{username}")
    public List<Order> getAllOrdersForUser(@PathVariable String username)
    {
        try {
            return this.orderService.getOrdersForUser(username);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
    @GetMapping("/activeOrder/{username}")
    public ResponseEntity<Order> getActiveOrderForUser(@PathVariable String username)
    {
        try {
            return this.orderService.getActiveOrderForUser(username)
                    .map(order -> ResponseEntity.ok().body(order))
                    .orElseGet(()->ResponseEntity.notFound().build());
        } catch (UserNotFoundException | UserCanNotHave2ActiveOrders e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
    @GetMapping("/processing")
    public List<Order> getAllProcessingOrders()
    {
        return this.orderService.getAllProcessingOrders();
    }
    @GetMapping("/delivering")
    public List<Order> getAllDeliveringOrders()
    {
        return this.orderService.getAllDeliveringOrders();
    }
    
    @GetMapping("/pay/{id}")
    public ResponseEntity<Order> payForOrder(@PathVariable Long id)
    {
        try {
            return this.orderService.payForOrder(id)
                    .map(order -> ResponseEntity.ok().body(order))
                    .orElseGet(()->ResponseEntity.notFound().build());
        } catch (OrderNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
    @GetMapping("/cancel/{username}")
    public ResponseEntity<Order> cancelOrder(@PathVariable String username)
    {
        try {
            return this.orderService.cancelOrder(username)
                    .map(order -> ResponseEntity.ok().body(order))
                    .orElseGet(()->ResponseEntity.notFound().build());
        } catch (UserNotFoundException | OrderNotFound | UserCanNotHave2ActiveOrders e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getDetailsForOrder(@PathVariable Long id)
    {
        try {
            return this.orderService.getDetailsForOrder(id)
                    .map(order -> ResponseEntity.ok().body(order))
                    .orElseGet(()->ResponseEntity.notFound().build());
        } catch (OrderNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
    @GetMapping("/topOrdered")
    public List<Meal> getTop5OrderedMeals()
    {
        return this.orderService.find5TopOrderedMeals();
    }
    @PostMapping("/addOrderItem")
    public ResponseEntity<OrderItem> addOrderItemInOrder(@RequestBody OrderItemDto orderItemDto)
    {
        try {
            return this.orderService.addNewOrderItemInOrder(orderItemDto)
                    .map(order -> ResponseEntity.ok().body(order))
                    .orElseGet(()->ResponseEntity.notFound().build());
        } catch (UserNotFoundException | UserCanNotHave2ActiveOrders | MealNotFound | MissingFieldException | EmptyDataException | OrderNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
    @GetMapping("/plusQuantity/{id}")
    public ResponseEntity<OrderItem> plusOrderItemQuantity(@PathVariable Long id)
    {
        try {
            return this.orderService.plusOrderItemQuantity(id)
                    .map(order -> ResponseEntity.ok().body(order))
                    .orElseGet(()->ResponseEntity.notFound().build());
        } catch (OrderItemNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
    @GetMapping("/minusQuantity/{id}")
    public ResponseEntity<OrderItem> minusOrderItemQuantity(@PathVariable Long id)
    {
        try {
            return this.orderService.minusOrderItemQuantity(id)
                    .map(order -> ResponseEntity.ok().body(order))
                    .orElseGet(()->ResponseEntity.notFound().build());
        } catch (OrderItemNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrderItemFromOrder(@PathVariable Long id)
    {
        boolean result= false;
        try {
            result = this.orderService.deleteOrderItemFromOrder(id);
        } catch (OrderItemNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
        if(result)
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/orderItems/{id}")
    public List<OrderItem> getOrderItemsForOrder(@PathVariable Long id)
    {
        try {
            return this.orderService.getAllOrderItemsInOrder(id);
        } catch (OrderNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
    @GetMapping("/orderItems/user/{username}")
    public List<OrderItem> getOrderItemsForUserActiveOrder(@PathVariable String username)
    {
        try {
            return this.orderService.getAllOrderedItemsForUserActiveOrder(username);
        } catch (UserNotFoundException | UserCanNotHave2ActiveOrders | OrderNotFound e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }
    }
}
