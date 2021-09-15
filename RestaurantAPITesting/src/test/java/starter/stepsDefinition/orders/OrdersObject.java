package starter.stepsDefinition.orders;

import starter.data.enumerations.OrderStatus;
import starter.data.enumerations.Role;
import starter.data.model.*;
import starter.stepsDefinition.authentication.AuthenticationObjects;
import starter.stepsDefinition.delivery.DeliveryObjects;
import starter.stepsDefinition.meals.MealsObjects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdersObject {

    public static Order AdminActiveOrder=new Order(1L, LocalDateTime.of(2021,07,17,23,12,50), OrderStatus.ACTIVE, AuthenticationObjects.expectedUser);

    public static Order PayForOrderWithId1=new Order(1L, LocalDateTime.of(2021,07,17,23,12,50), OrderStatus.PROCESSING, AuthenticationObjects.expectedUser);

    public static User customer2CancelOrder=
            new User("customer2@test.com","$2a$10$Oc3Ll1LSYnjCoSzKviy0H.y8MOSmIFt2JG9j2tfOxOOeEoeuhH.OS",
                    "Customer2 name","Customer2 surname","070123465", "Customer2 address", Role.ROLE_CUSTOMER,true,
                    true,true,true, DeliveryObjects.customerAuthorities);

    public static Order CancelOrderForCustomer2=new Order(4L,LocalDateTime.of(2021,07,23,01,23,51), OrderStatus.CANCELED,customer2CancelOrder);

    public static Order detailsForOrderWithId6=new Order(6L,LocalDateTime.of(2021,07,23,00,54,21),OrderStatus.DELIVERING,customer2CancelOrder);

    public static int addedOrderItemQuantity=3;
    public static Long addedOrderItemMealId=6L;
    public static String addedOrderItemUsername="admin@admin.com";

    public static MealCategory mealCategoryWithId1=new MealCategory(1L,"Seafood","https://www.nicepng.com/png/detail/71-715245_salmon-air-fryer-cookbook-the-best-quick-delicious.png");
    public static Ingredient ingredient4=new Ingredient(4L,45,"Pepper");
    public static Ingredient ingredient5=new Ingredient(5L,45,"Onion");
    public static Ingredient ingredient8=new Ingredient(8L,233,"Lettuce");
    public static Ingredient ingredient29=new Ingredient(29L,23,"Salmon");
    public static Ingredient ingredient31=new Ingredient(31L,85,"Shrimps");
    public static List<Ingredient> ingredientsForMeal5;

    static {
        ingredientsForMeal5 = new ArrayList<>();
        ingredientsForMeal5.add(ingredient4);
        ingredientsForMeal5.add(ingredient5);
        ingredientsForMeal5.add(ingredient8);
        ingredientsForMeal5.add(ingredient29);
        ingredientsForMeal5.add(ingredient31);
    }

    public static Meal mealWithId5=new Meal(5L,"Meal3","Meal3 description",32,mealCategoryWithId1,"https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15548.jpg?ext=.jpg",ingredientsForMeal5);

    public static Order OrderWithId1=new Order(1L, LocalDateTime.of(2021,07,17,23,12,50), OrderStatus.ACTIVE, AuthenticationObjects.expectedUser);


    public static OrderItem OrderItemWithId2Plus=new OrderItem(2L,4,OrderWithId1,mealWithId5);


    public static OrderItem OrderItemWithId1Minus=new OrderItem(1L,1,OrderWithId1, MealsObjects.mealWithId3);

}
