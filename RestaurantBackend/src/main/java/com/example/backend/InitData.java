package com.example.backend;

import com.example.backend.model.*;
import com.example.backend.model.enumerations.OrderStatus;
import com.example.backend.model.enumerations.Role;
import com.example.backend.repository.*;
import com.example.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class InitData {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final IngredientRepository ingredientRepository;
    private final MealCategoryRepository mealCategoryRepository;
    private final MealRepository mealRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final DeliveryRepository deliveryRepository;

    @PostConstruct
    public void initData()
    {
        //First run
//        initUsers();
//        initIngredients();
//        initMealCategories();

        //Second run
//        initMeals();
//        initOrders();

        //Third run
//        initOrderItems();
//        initDeliveries();
    }

    public void initUsers(){
        String encodedPassword=passwordEncoder.encode("P@ssword");
        User user=new User("admin@admin.com",encodedPassword,"Admin name","Admin surname","070123465",
                "Admin address", Role.ROLE_ADMIN);
        userRepository.save(user);

        encodedPassword=passwordEncoder.encode("P@ssword");
        user=new User("employee@test.com",encodedPassword,"Employee name","Employee surname","070123465",
                "Employee address", Role.ROLE_EMPLOYEE);
        userRepository.save(user);

        encodedPassword=passwordEncoder.encode("P@ssword");
        user=new User("delivery@test.com",encodedPassword,"Delivery name","Delivery surname","070123465",
                "Delivery address", Role.ROLE_DELIVERY);
        userRepository.save(user);

        encodedPassword=passwordEncoder.encode("P@ssword");
        user=new User("customer1@test.com",encodedPassword,"Customer1 name","Customer1 surname","070123465",
                "Customer1 address", Role.ROLE_CUSTOMER);
        userRepository.save(user);

        encodedPassword=passwordEncoder.encode("P@ssword");
        user=new User("customer2@test.com",encodedPassword,"Customer2 name","Customer2 surname","070123465",
                "Customer2 address", Role.ROLE_CUSTOMER);
        userRepository.save(user);
    }

    public void initIngredients()
    {
        Ingredient ingredient=new Ingredient("Cheese",80);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Pepperoni",50);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Mushrooms",45);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Pepper",45);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Onion",45);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Mozzarella",85);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Tuna",45);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Lettuce",233);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Chicken",87);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Croutons",45);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Carrot",74);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Corn",122);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Cucumber",45);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Egg",123);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Bacon",85);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Ketchup",25);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Pancake",45);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Waffle",78);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Apple",45);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Honey",85);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Cinnamon",32);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Rice",45);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Macaroni",78);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Spaghetti",85);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Potato",85);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Lime",45);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Pork",85);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Turkey",65);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Salmon",23);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Fish",23);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Shrimps",85);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Tomato",98);
        ingredientRepository.save(ingredient);
        ingredient=new Ingredient("Milk",222);
        ingredientRepository.save(ingredient);

    }

    public void initMealCategories()
    {
        MealCategory mealCategory=new MealCategory("Seafood","https://www.nicepng.com/png/detail/71-715245_salmon-air-fryer-cookbook-the-best-quick-delicious.png");
        mealCategoryRepository.save(mealCategory);
        mealCategory=new MealCategory("Pizza","https://hips.hearstapps.com/wdy.h-cdn.co/assets/cm/15/09/54ef8b64366c1_-_fresh-tomato-pizza-kqgv52-xl.jpg");
        mealCategoryRepository.save(mealCategory);
        mealCategory=new MealCategory("Dessert","https://pbs.twimg.com/profile_images/645988651109904384/3ljrC_8j_400x400.jpg");
        mealCategoryRepository.save(mealCategory);
        mealCategory=new MealCategory("Sandwich","https://hips.hearstapps.com/wdy.h-cdn.co/assets/cm/15/09/54ef9289a07b0_-_vietnamese-turkey-sandwich-recipe-wdy1113-de.jpg");
        mealCategoryRepository.save(mealCategory);
        mealCategory=new MealCategory("Breakfast","https://assets.epicurious.com/photos/5e95fb10febe90000898aff8/1:1/w_400%2Cc_limit/PlantainBreakfast_HERO_041020_6266.jpg");
        mealCategoryRepository.save(mealCategory);
        mealCategory=new MealCategory("Salad","https://www.waitrose.com/content/dam/waitrose/recipes/images/m/Malaysian-style-rojak-pineapple-salad.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png");
        mealCategoryRepository.save(mealCategory);
        mealCategory=new MealCategory("Pasta","https://i.pinimg.com/originals/ae/ce/cf/aececf995c0549a49e82e38a1bb3a586.jpg");
        mealCategoryRepository.save(mealCategory);
        mealCategory=new MealCategory("Burger","https://tastesbetterfromscratch.com/wp-content/uploads/2016/03/Black-Bean-Burger-8-400x400.jpg");
        mealCategoryRepository.save(mealCategory);
        mealCategory=new MealCategory("Main meal","https://assets.epicurious.com/photos/5dc7263dd482f10008d4fadb/1:1/w_400%2Cc_limit/HoneyMustardChicken_RECIPE_110519_5882.jpg");
        mealCategoryRepository.save(mealCategory);
        mealCategory=new MealCategory("Meal category for edit","https://i.pinimg.com/originals/c9/c2/b1/c9c2b12a2b325f0080a1f328a0963341.jpg");
        mealCategoryRepository.save(mealCategory);
        mealCategory=new MealCategory("Meal category for delete","https://assets.simpleviewinc.com/simpleview/image/upload/c_fill,h_400,q_40,w_400/v1/clients/richmondbc/onceuponatime_90_DSCF2732_d974c753-6b91-4db8-abc4-cf8a1b9ef32d.jpg");
        mealCategoryRepository.save(mealCategory);
    }

    public void initMeals()
    {
        MealCategory mealCategory=this.mealCategoryRepository.findById(2L).get();
        List<Long> ingredientId= Arrays.asList(1L,2L,3L,4L);
        List<Ingredient> ingredients=this.ingredientRepository.findAllById(ingredientId);
        Meal meal=new Meal("Meal for delete","Meal for delete description",120,mealCategory,"https://hips.hearstapps.com/wdy.h-cdn.co/assets/cm/15/09/54ef8b64366c1_-_fresh-tomato-pizza-kqgv52-xl.jpg",ingredients);
        mealRepository.save(meal);
        mealCategory=this.mealCategoryRepository.findById(6L).get();
        ingredientId= Arrays.asList(5L,6L,7L,8L);
        ingredients=this.ingredientRepository.findAllById(ingredientId);
        meal=new Meal("Meal for edit","Meal for edit description",122,mealCategory,"https://food-guide.canada.ca/sites/default/files/styles/square_400_x_400/public/2020-12/CFGPlate-crop400x400.jpg",ingredients);
        mealRepository.save(meal);
        mealCategory=this.mealCategoryRepository.findById(7L).get();
        ingredientId= Arrays.asList(1L,16L,24L,15L);
        ingredients=this.ingredientRepository.findAllById(ingredientId);
        meal=new Meal("Meal1","Meal1 description",50,mealCategory,"https://www.bosscaffe.com/sites/default/files/styles/product_thumb/public/2019-04/PASTA_BOLOGNESE.jpg?itok=xlPQ0A54",ingredients);
        mealRepository.save(meal);
        mealCategory=this.mealCategoryRepository.findById(5L).get();
        ingredientId= Arrays.asList(18L,20L,19L);
        ingredients=this.ingredientRepository.findAllById(ingredientId);
        meal=new Meal("Meal2","Meal2 description",10,mealCategory,"https://hips.hearstapps.com/del.h-cdn.co/assets/cm/15/10/54f63ec6bc6d3_-_gluten-free-banana-coconut-pancakes-recipe-fw0814-de.jpg",ingredients);
        mealRepository.save(meal);
        mealCategory=this.mealCategoryRepository.findById(1L).get();
        ingredientId= Arrays.asList(4L,5L,8L,29L,31L);
        ingredients=this.ingredientRepository.findAllById(ingredientId);
        meal=new Meal("Meal3","Meal3 description",32,mealCategory,"https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15548.jpg?ext=.jpg",ingredients);
        mealRepository.save(meal);
        mealCategory=this.mealCategoryRepository.findById(2L).get();
        ingredientId= Arrays.asList(1L,2L,3L,6L,16L,32L);
        ingredients=this.ingredientRepository.findAllById(ingredientId);
        meal=new Meal("Meal4","Meal4 description",12,mealCategory,"https://www.woolwichdairy.com/-/media/ecosystem/divisions/canada-dairy/sites/woolwich-dairy/woolwich-dairy-images/recipes/pinterest/classic-veggie-pizza-400x400.ashx?revision=edb57bc9-56e4-4a68-9624-ee641b9d13ed",ingredients);
        mealRepository.save(meal);
        mealCategory=this.mealCategoryRepository.findById(9L).get();
        ingredientId= Arrays.asList(9L,8L,15L,22L,27L);
        ingredients=this.ingredientRepository.findAllById(ingredientId);
        meal=new Meal("Meal5","Meal5 description",50,mealCategory,"https://www.bbcgoodfoodme.com/assets/recipes/25261/original/tenderloin.png",ingredients);
        mealRepository.save(meal);
        mealCategory=this.mealCategoryRepository.findById(3L).get();
        ingredientId= Arrays.asList(17L,20L,21L,33L);
        ingredients=this.ingredientRepository.findAllById(ingredientId);
        meal=new Meal("Meal6","Meal6 description",50,mealCategory,"https://www.readyseteat.com/sites/g/files/qyyrlu501/files/uploadedImages/img_2059_952.jpg",ingredients);
        mealRepository.save(meal);
        mealCategory=this.mealCategoryRepository.findById(6L).get();
        ingredientId= Arrays.asList(4L,5L,8L,9L,10L,12L,13L,32L);
        ingredients=this.ingredientRepository.findAllById(ingredientId);
        meal=new Meal("Meal7","Meal7 description",20,mealCategory,"https://img.allw.mn/food/thumbs/y8/yv/v4wvynv2_400x400.jpg",ingredients);
        mealRepository.save(meal);
        mealCategory=this.mealCategoryRepository.findById(8L).get();
        ingredientId= Arrays.asList(5L,8L,15L,25L,27L);
        ingredients=this.ingredientRepository.findAllById(ingredientId);
        meal=new Meal("Meal8","Meal8 description",27,mealCategory,"https://www.waitrose.com/content/dam/waitrose/recipes/images/b/E2E_WaitroseWeekendSunshineSpecial_36117_BestBurgers.gif/_jcr_content/renditions/cq5dam.thumbnail.400.400.png",ingredients);
        mealRepository.save(meal);
        mealCategory=this.mealCategoryRepository.findById(7L).get();
        ingredientId= Arrays.asList(23L,16L,31L,3L);
        ingredients=this.ingredientRepository.findAllById(ingredientId);
        meal=new Meal("Meal9","Meal9 description",32,mealCategory,"https://taste.co.za/wp-content/uploads/2015/03/fiorelli-pasta-with-smoked-salmon-and-cavi-art-3650-400x400.jpg",ingredients);
        mealRepository.save(meal);
        mealCategory=this.mealCategoryRepository.findById(4L).get();
        ingredientId= Arrays.asList(1L,8L,13L,28L);
        ingredients=this.ingredientRepository.findAllById(ingredientId);
        meal=new Meal("Meal10","Meal10 description",10,mealCategory,"https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15484.jpg?ext=.jpg",ingredients);
        mealRepository.save(meal);

    }

    public void initOrders()
    {
        User user=this.userRepository.findByUsername("admin@admin.com").get();
        Order order=new Order(LocalDateTime.of(2021,07,17,23,12,50), OrderStatus.ACTIVE,user);
        orderRepository.save(order);
        order=new Order(LocalDateTime.of(2021,07,17,20,14,26),OrderStatus.PROCESSING,user);
        orderRepository.save(order);
        order=new Order(LocalDateTime.of(2021,07,17,19,50,50),OrderStatus.DELIVERING,user);
        orderRepository.save(order);
        user=this.userRepository.findByUsername("customer2@test.com").get();
        order=new Order(LocalDateTime.of(2021,07,23,01,23,51), OrderStatus.ACTIVE,user);
        orderRepository.save(order);
        order=new Order(LocalDateTime.of(2021,07,23,01,23,05),OrderStatus.PROCESSING,user);
        orderRepository.save(order);
        order=new Order(LocalDateTime.of(2021,07,23,00,54,21),OrderStatus.DELIVERING,user);
        orderRepository.save(order);
        user=this.userRepository.findByUsername("customer1@test.com").get();
        order=new Order(LocalDateTime.of(2021,07,30,01,23,51), OrderStatus.ACTIVE,user);
        orderRepository.save(order);
        order=new Order(LocalDateTime.of(2021,07,25,01,23,05),OrderStatus.PROCESSING,user);
        orderRepository.save(order);
    }

    public void initOrderItems()
    {
        Order order=this.orderRepository.findById(1L).get();
        Meal meal=this.mealRepository.findById(3L).get();
        OrderItem orderItem=new OrderItem(2,order,meal);
        this.orderItemRepository.save(orderItem);
        order=this.orderRepository.findById(1L).get();
        meal=this.mealRepository.findById(5L).get();
        orderItem=new OrderItem(3,order,meal);
        this.orderItemRepository.save(orderItem);
        order=this.orderRepository.findById(2L).get();
        meal=this.mealRepository.findById(4L).get();
        orderItem=new OrderItem(5,order,meal);
        this.orderItemRepository.save(orderItem);
        order=this.orderRepository.findById(2L).get();
        meal=this.mealRepository.findById(6L).get();
        orderItem=new OrderItem(4,order,meal);
        this.orderItemRepository.save(orderItem);
        order=this.orderRepository.findById(3L).get();
        meal=this.mealRepository.findById(7L).get();
        orderItem=new OrderItem(1,order,meal);
        this.orderItemRepository.save(orderItem);
        order=this.orderRepository.findById(3L).get();
        meal=this.mealRepository.findById(9L).get();
        orderItem=new OrderItem(8,order,meal);
        this.orderItemRepository.save(orderItem);
        order=this.orderRepository.findById(4L).get();
        meal=this.mealRepository.findById(8L).get();
        orderItem=new OrderItem(4,order,meal);
        this.orderItemRepository.save(orderItem);
        order=this.orderRepository.findById(4L).get();
        meal=this.mealRepository.findById(10L).get();
        orderItem=new OrderItem(5,order,meal);
        this.orderItemRepository.save(orderItem);
        order=this.orderRepository.findById(5L).get();
        meal=this.mealRepository.findById(5L).get();
        orderItem=new OrderItem(1,order,meal);
        this.orderItemRepository.save(orderItem);
        order=this.orderRepository.findById(5L).get();
        meal=this.mealRepository.findById(11L).get();
        orderItem=new OrderItem(6,order,meal);
        this.orderItemRepository.save(orderItem);
        order=this.orderRepository.findById(6L).get();
        meal=this.mealRepository.findById(10L).get();
        orderItem=new OrderItem(3,order,meal);
        this.orderItemRepository.save(orderItem);
        order=this.orderRepository.findById(6L).get();
        meal=this.mealRepository.findById(12L).get();
        orderItem=new OrderItem(2,order,meal);
        this.orderItemRepository.save(orderItem);
        order=this.orderRepository.findById(8L).get();
        meal=this.mealRepository.findById(11L).get();
        orderItem=new OrderItem(2,order,meal);
        this.orderItemRepository.save(orderItem);
    }

    public void initDeliveries()
    {
        User user=this.userRepository.findByUsername("admin@admin.com").get();
        Order order=this.orderRepository.findById(3L).get();
        Delivery delivery=new Delivery(user.getAddress(),LocalDateTime.of(2021,11,02,15,30,25),order);
        this.deliveryRepository.save(delivery);
        user=this.userRepository.findByUsername("customer2@test.com").get();
        order=this.orderRepository.findById(6L).get();
        delivery=new Delivery(user.getAddress(),LocalDateTime.of(2021,10,02,17,30,25),order);
        this.deliveryRepository.save(delivery);
    }


}
