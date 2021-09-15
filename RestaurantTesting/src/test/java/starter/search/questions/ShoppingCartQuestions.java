package starter.search.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Value;
import starter.search.targets.ShoppingCartTargets;

import java.util.List;

public class ShoppingCartQuestions {

    public static Question<String> totalPriceForOrder(){
        return Text.of(ShoppingCartTargets.TOTAL_PRICE_CHECK_OUT)
                .asAString();
    }

    public static Question<String> deliveryFee(){
        return Text.of(ShoppingCartTargets.DELIVERY_FEE)
                .asAString();
    }

    public static Question<String> totalWithDelivery(){
        return Text.of(ShoppingCartTargets.TOTAL_WITH_DELIVERY_FEE)
                .asAString();
    }

    public static Question<List<String>> mealNamesInShoppingCart(){
        return Text.of(ShoppingCartTargets.MEAL_NAME_COLUMN)
                .asAList();
    }
    public static Question<List<String>> mealPriceInShoppingCart(){
        return Text.of(ShoppingCartTargets.MEAL_PRICE_COLUMN)
                .asAList();
    }
    public static Question<List<String>> mealQuantityInShoppingCart(){
        return Value.of(ShoppingCartTargets.MEAL_QUANTITY_COLUMN)
                .asAList();
    }
    public static Question<List<String>> mealSubtotalInShoppingCart(){
        return Text.of(ShoppingCartTargets.ORDER_SUBTOTAL_COLUMN)
                .asAList();
    }
    public static Question<String> orderTotal(){
        return Text.of(ShoppingCartTargets.ORDER_TOTAL)
                .asAString();
    }

    public static Question<String> mealInShoppingCartName(String name){
        if(name.equals("Meal8"))
            return Text.of(ShoppingCartTargets.MEAL8_NAME_IN_SHOPPING_CART)
            .asAString();
        return Text.of(ShoppingCartTargets.MEAL7_NAME_IN_SHOPPING_CART)
                .asAString();
    }

    public static Question<String> mealInShoppingCartQuantity(String name){
        if(name.equals("Meal8"))
            return Value.of(ShoppingCartTargets.MEAL8_QUANTITY_FIELD_IN_SHOPPING_CART)
                    .asAString();
        return Value.of(ShoppingCartTargets.MEAL7_QUANTITY_FIELD_IN_SHOPPING_CART)
                .asAString();
    }

    public static Question<String> mealInShoppingCartSubtotal(String name){
        if(name.equals("Meal8"))
            return Text.of(ShoppingCartTargets.MEAL8_SUBTOTAL_IN_SHOPPING_CART)
                    .asAString();
        return Text.of(ShoppingCartTargets.MEAL7_SUBTOTAL_IN_SHOPPING_CART)
                .asAString();
    }
}
