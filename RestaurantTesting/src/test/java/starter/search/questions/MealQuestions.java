package starter.search.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Value;
import starter.search.targets.DeliveryTargets;
import starter.search.targets.MealTargets;

import java.util.List;

public class MealQuestions {

    public static Question<List<String>> mealDetailsIngredients()
    {
        return Text.of(MealTargets.MEAL_DETAILS_INGREDIENTS)
                .describedAs("list of ingredients")
                .asAList();
    }

    public static Question<String> Meal2QuantityFieldValue(){
        return Value.of(MealTargets.MEAL2_QUANTITY_FIELD)
                .describedAs("quantity field for meal2")
                .asAString();
    }

    public static Question<List<String>> mostOrderedMealsName(){
        return Text.of(".menuItemName")
                .describedAs("names of most ordered meals")
                .asAList();
    }

    public static Question<String> mealInShoppingCartName(String name){
        if (name.equals("Meal2"))
            return Text.of(MealTargets.MEAL2_IN_SHOPPING_CART_NAME_FIELD)
                    .asAString();
        if(name.equals("Meal3"))
            return Text.of(MealTargets.MEAL3_IN_SHOPPING_CART_NAME_FIELD)
                    .asAString();
        if (name.equals("Meal4"))
            return Text.of(MealTargets.MEAL4_IN_SHOPPING_CART_NAME_FIELD)
                .asAString();
        return Text.of(MealTargets.MEAL7_IN_SHOPPING_CART_NAME_FIELD)
                .asAString();
    }

    public static Question<String> mealInShoppingCartQuantity(String name){
        if(name.equals("Meal2"))
            return Value.of(MealTargets.MEAL2_IN_SHOPPING_CART_QUANTITY_FIELD)
                    .asAString();
        if(name.equals("Meal3"))
            return Value.of(MealTargets.MEAL3_IN_SHOPPING_CART_QUANTITY_FIELD)
                    .asAString();
        if(name.equals("Meal4"))
            return Value.of(MealTargets.MEAL4_IN_SHOPPING_CART_QUANTITY_FIELD)
                .asAString();
        return Value.of(MealTargets.MEAL7_IN_SHOPPING_CART_QUANTITY_FIELD)
                .asAString();
    }
}
