package com.example.backend.service.impl;

import com.example.backend.model.Meal;
import com.example.backend.model.Order;
import com.example.backend.model.OrderItem;
import com.example.backend.model.User;
import com.example.backend.model.dto.OrderItemDto;
import com.example.backend.model.enumerations.OrderStatus;
import com.example.backend.model.exceptions.*;
import com.example.backend.repository.MealRepository;
import com.example.backend.repository.OrderItemRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Optional<Order> creteNewOrder(LocalDateTime orderedOn,  OrderStatus orderStatus, String username) throws UserNotFoundException {
        User user=this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Order order=new Order(orderedOn,orderStatus,user);
        return Optional.of(this.orderRepository.save(order));
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersForUser(String username) throws UserNotFoundException {
        User user=this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return this.orderRepository.findAllByOrderedByUser(user);
    }

    @Override
    public List<Order> getAllProcessingOrders() {
        return this.orderRepository.findAllByOrderStatus(OrderStatus.PROCESSING);
    }

    @Override
    public List<Order> getAllDeliveringOrders() {
        return this.orderRepository.findAllByOrderStatus(OrderStatus.DELIVERING);
    }

    @Override
    public Optional<Order> payForOrder(Long orderId) throws OrderNotFound {
        return this.changeOrderStatus(orderId,OrderStatus.PROCESSING);
    }

    @Override
    public Optional<Order> payForUserOrder(String username) throws UserNotFoundException, OrderNotFound, UserCanNotHave2ActiveOrders {
        Order order=this.getActiveOrderForUser(username).orElseThrow(OrderNotFound::new);
        return this.changeOrderStatus(order.getId(),OrderStatus.PROCESSING);
    }

    @Override
    public Optional<Order> cancelOrder(String username) throws UserNotFoundException, OrderNotFound, UserCanNotHave2ActiveOrders {
        Order order=this.getActiveOrderForUser(username).orElseThrow(OrderNotFound::new);
        return this.changeOrderStatus(order.getId(),OrderStatus.CANCELED);
    }

    @Override
    public Optional<Order> changeOrderStatus(Long orderId, OrderStatus orderStatus) throws OrderNotFound {
        Order order=this.orderRepository.findById(orderId).orElseThrow(OrderNotFound::new);
        order.setOrderStatus(orderStatus);
        return Optional.of(this.orderRepository.save(order));
    }

    @Override
    public Optional<Order> getActiveOrderForUser(String username) throws UserNotFoundException, UserCanNotHave2ActiveOrders {
        User user=this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        List<Order> orders=this.orderRepository.findAllByOrderedByUser(user)
                .stream().filter(each->each.getOrderStatus().equals(OrderStatus.ACTIVE))
                .collect(Collectors.toList());
        if(orders.size()==0)
        {
            //bidejki nema aktivna naracka, istata se kreira za najaveniot korisnik
            return this.creteNewOrder(LocalDateTime.now(),OrderStatus.ACTIVE,username);
        }
        else if(orders.size()>1)
        {
            throw new UserCanNotHave2ActiveOrders();
        }
        else
        {
            return Optional.of(orders.get(0));
        }
    }

    @Override
    public Optional<Order> getDetailsForOrder(Long orderId) throws OrderNotFound {
        Optional<Order> order= this.orderRepository.findById(orderId);
        if(order.isEmpty())
            throw new OrderNotFound();
        return order;
    }

    @Override
    public List<Meal> find5TopOrderedMeals() {
        TreeMap<Long,Integer> numberOfOrdersForEachMeal=new TreeMap<>();
        for(Meal m: this.mealRepository.findAll())
        {
            numberOfOrdersForEachMeal.put(m.getId(),this.numberOfOrdersForMeal(m));
        }
        final Map<Long,Integer> mapa = numberOfOrdersForEachMeal.entrySet()
                .stream()
                .sorted((Map.Entry.<Long, Integer>comparingByValue().reversed()))
                .limit(4)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return this.mealRepository.findAllById(mapa.keySet());
    }

    @Override
    public Optional<OrderItem> addNewOrderItemInOrder(OrderItemDto orderItemDto) throws UserNotFoundException, UserCanNotHave2ActiveOrders, OrderNotFound, MealNotFound, MissingFieldException, EmptyDataException {
        if(orderItemDto.getMealId()==null || orderItemDto.getQuantity()==null || orderItemDto.getUsername()==null)
            throw new MissingFieldException();
        if(orderItemDto.getMealId()==0 || orderItemDto.getQuantity()==0 || orderItemDto.getUsername().isEmpty())
            throw new EmptyDataException();
        Order order=this.getActiveOrderForUser(orderItemDto.getUsername()).orElseThrow(OrderNotFound::new);
        Meal meal=this.mealRepository.findById(orderItemDto.getMealId()).orElseThrow(MealNotFound::new);
        OrderItem orderItem=new OrderItem(orderItemDto.getQuantity(),order,meal);
        this.orderItemRepository.save(orderItem);
        return Optional.of(orderItem);
    }

    @Override
    public Optional<OrderItem> plusOrderItemQuantity(Long orderItemId) throws OrderItemNotFound {
        OrderItem orderItem=this.orderItemRepository.findById(orderItemId).orElseThrow(OrderItemNotFound::new);
        orderItem.setQuantity(orderItem.getQuantity()+1);
        this.orderItemRepository.save(orderItem);
        return Optional.of(orderItem);
    }

    @Override
    public Optional<OrderItem> minusOrderItemQuantity(Long orderItemId) throws OrderItemNotFound {
        OrderItem orderItem=this.orderItemRepository.findById(orderItemId).orElseThrow(OrderItemNotFound::new);
        orderItem.setQuantity(orderItem.getQuantity()-1);
        this.orderItemRepository.save(orderItem);
        return Optional.of(orderItem);
    }

    @Override
    public boolean deleteOrderItemFromOrder(Long orderItemId) throws OrderItemNotFound {
        OrderItem orderItem=this.orderItemRepository.findById(orderItemId).orElseThrow(OrderItemNotFound::new);
        this.orderItemRepository.delete(orderItem);
        return this.orderItemRepository.findById(orderItemId).isEmpty();
    }

    @Override
    public List<OrderItem> getAllOrderItemsInOrder(Long orderId) throws OrderNotFound {
        Order order=this.orderRepository.findById(orderId).orElseThrow(OrderNotFound::new);
        return this.orderItemRepository.findAllByItemInOrder(order);
    }

    @Override
    public List<OrderItem> getAllOrderedItemsForUserActiveOrder(String username) throws UserNotFoundException, UserCanNotHave2ActiveOrders, OrderNotFound {
        Order order=this.getActiveOrderForUser(username).orElseThrow(OrderNotFound::new);
        return this.orderItemRepository.findAllByItemInOrder(order);
    }

    @Override
    public int numberOfOrdersForMeal(Meal meal) {
        return this.orderItemRepository.findAllByOrderItemForMeal(meal).stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    @Override
    public double calculatePriceForOrder(Long orderId) throws OrderNotFound {
        return this.getAllOrderItemsInOrder(orderId)
                .stream()
                .mapToDouble(each->Math.round((each.getOrderItemForMeal().getPrice()*each.getQuantity()) * 100.0) / 100.0)
                .sum();
    }
}
