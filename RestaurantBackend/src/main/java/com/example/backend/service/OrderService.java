package com.example.backend.service;

import com.example.backend.model.Meal;
import com.example.backend.model.Order;
import com.example.backend.model.OrderItem;
import com.example.backend.model.User;
import com.example.backend.model.dto.OrderItemDto;
import com.example.backend.model.enumerations.OrderStatus;
import com.example.backend.model.exceptions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> creteNewOrder(LocalDateTime orderedOn, OrderStatus orderStatus, String username) throws UserNotFoundException;
    List<Order> getAllOrders();
    List<Order> getOrdersForUser(String username) throws UserNotFoundException;
    List<Order> getAllProcessingOrders();
    List<Order> getAllDeliveringOrders();
    Optional<Order> payForOrder(Long orderId) throws OrderNotFound;
    Optional<Order> payForUserOrder(String username) throws UserNotFoundException, OrderNotFound, UserCanNotHave2ActiveOrders;
    Optional<Order> cancelOrder(String username) throws UserNotFoundException, OrderNotFound, UserCanNotHave2ActiveOrders;
    Optional<Order> changeOrderStatus(Long orderId, OrderStatus orderStatus) throws OrderNotFound;
    Optional<Order> getActiveOrderForUser(String username) throws UserNotFoundException, UserCanNotHave2ActiveOrders;
    Optional<Order> getDetailsForOrder(Long orderId) throws OrderNotFound;
    List<Meal> find5TopOrderedMeals();
    Optional<OrderItem> addNewOrderItemInOrder(OrderItemDto orderItemDto) throws UserNotFoundException, UserCanNotHave2ActiveOrders, OrderNotFound, MealNotFound, MissingFieldException, EmptyDataException;
    Optional<OrderItem> plusOrderItemQuantity(Long orderItemId) throws OrderItemNotFound;
    Optional<OrderItem> minusOrderItemQuantity(Long orderItemId) throws OrderItemNotFound;
    boolean deleteOrderItemFromOrder(Long orderItemId) throws OrderItemNotFound;
    List<OrderItem> getAllOrderItemsInOrder(Long orderId) throws OrderNotFound;
    List<OrderItem> getAllOrderedItemsForUserActiveOrder(String username) throws UserNotFoundException, UserCanNotHave2ActiveOrders, OrderNotFound;
    int numberOfOrdersForMeal(Meal meal);
    double calculatePriceForOrder(Long orderId) throws OrderNotFound;
}
