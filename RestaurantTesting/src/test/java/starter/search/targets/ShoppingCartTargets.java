package starter.search.targets;

import net.serenitybdd.screenplay.targets.Target;

public class ShoppingCartTargets {

    public static Target TOTAL_PRICE_CHECK_OUT=Target.the("total")
            .locatedBy("#totalPrice");
    public static Target DELIVERY_FEE=Target.the("delivery")
            .locatedBy("#deliveryFee span");
    public static Target TOTAL_WITH_DELIVERY_FEE=Target.the("total plus delivery")
            .locatedBy("#totalWithDelivery");

    public static Target ITEM_IN_SHOPPING_CART_ROW=Target.the("row")
            .locatedBy(".itemInShoppingCartRow");

    public static Target MEAL_NAME_COLUMN=Target.the("column")
            .locatedBy(".mealName");
    public static Target MEAL_PRICE_COLUMN=Target.the("column")
            .locatedBy(".mealPrice");
    public static Target MEAL_QUANTITY_COLUMN=Target.the("column")
            .locatedBy(".mealQuantity");
    public static Target ORDER_SUBTOTAL_COLUMN=Target.the("column")
            .locatedBy(".mealSubtotal");

    public static Target ORDER_TOTAL=Target.the("total")
            .locatedBy("#orderTotal");

    public static Target MEAL8_PLUS_BUTTON_IN_SHOPPING_CART=Target.the("plus button")
            .locatedBy(".plusOrderItem-8");
    public static Target MEAL8_QUANTITY_FIELD_IN_SHOPPING_CART=Target.the("quantity")
            .locatedBy(".quantityOrderItem-8");
    public static Target MEAL8_NAME_IN_SHOPPING_CART=Target.the("name")
            .locatedBy(".mealInShoppingCartName-Meal8");
    public static Target MEAL8_SUBTOTAL_IN_SHOPPING_CART=Target.the("subtotal")
            .locatedBy(".mealInShoppingCartSubtotal-Meal8");

    public static Target MEAL7_MINUS_BUTTON_IN_SHOPPING_CART=Target.the("minus button")
            .locatedBy(".minusOrderItem-15");
    public static Target MEAL7_QUANTITY_FIELD_IN_SHOPPING_CART=Target.the("quantity")
            .locatedBy(".quantityOrderItem-15");
    public static Target MEAL7_NAME_IN_SHOPPING_CART=Target.the("name")
            .locatedBy(".mealInShoppingCartName-Meal7");
    public static Target MEAL7_SUBTOTAL_IN_SHOPPING_CART=Target.the("subtotal")
            .locatedBy(".mealInShoppingCartSubtotal-Meal7");

    public static Target MEAL6_DELETE_BUTTON=Target.the("delete")
            .locatedBy(".deleteOrderItem-7");

    public static Target CONTINUE_SHOPPING_BUTTON=Target.the("continueShopping")
            .locatedBy("#continueShopping");
}
