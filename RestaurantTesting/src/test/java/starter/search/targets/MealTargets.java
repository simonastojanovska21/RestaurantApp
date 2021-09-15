package starter.search.targets;

import net.serenitybdd.screenplay.targets.Target;

public class MealTargets {

    public static Target MEAL_DETAILS_INGREDIENTS=Target.the("meal details image")
            .locatedBy(".mealDetailsIngredients-4");
    public static Target MOST_ORDERED_MEALS=Target.the("most ordered meals field")
            .locatedBy(".mostOrderedMeals");

    public static Target MEAL2_MINUS_BUTTON=Target.the("add to shopping cart minus button")
            .locatedBy(".minusButton-4");
    public static Target MEAL2_PLUS_BUTTON=Target.the("add to shopping cart plus button")
            .locatedBy(".plusButton-4");
    public static Target MEAL2_ADD_TO_SHOPPING_CART_BUTTON=Target.the("add to shopping cart button")
            .locatedBy(".addButton-4");
    public static Target MEAL2_QUANTITY_FIELD=Target.the("quantity text field")
            .locatedBy(".quantityField-4");

    public static Target MEAL7_PLUS_BUTTON=Target.the("add to shopping cart plus button")
            .locatedBy(".plusButton-9");
    public static Target MEAL7_ADD_TO_SHOPPING_CART_BUTTON=Target.the("add to shopping cart button")
            .locatedBy(".addButton-9");

    public static Target MEAL2_IN_SHOPPING_CART_QUANTITY_FIELD=Target.the("quantity input")
            .locatedBy(".mealInShoppingCartQuantity-Meal2 div input");
    public static Target MEAL2_IN_SHOPPING_CART_NAME_FIELD=Target.the("div containing name")
            .locatedBy(".mealInShoppingCartName-Meal2");

    public static Target MEAL7_IN_SHOPPING_CART_QUANTITY_FIELD=Target.the("quantity input")
            .locatedBy(".mealInShoppingCartQuantity-Meal7 div input");
    public static Target MEAL7_IN_SHOPPING_CART_NAME_FIELD=Target.the("div containing name")
            .locatedBy(".mealInShoppingCartName-Meal7");

    public static Target MEAL3_IN_SHOPPING_CART_QUANTITY_FIELD=Target.the("quantity input")
            .locatedBy(".mealInShoppingCartQuantity-Meal3 div input");
    public static Target MEAL3_IN_SHOPPING_CART_NAME_FIELD=Target.the("div containing name")
            .locatedBy(".mealInShoppingCartName-Meal3");

    public static Target MEAL4_IN_SHOPPING_CART_QUANTITY_FIELD=Target.the("quantity input")
            .locatedBy(".mealInShoppingCartQuantity-Meal4 div input");
    public static Target MEAL4_IN_SHOPPING_CART_NAME_FIELD=Target.the("div containing name")
            .locatedBy(".mealInShoppingCartName-Meal4");
}
