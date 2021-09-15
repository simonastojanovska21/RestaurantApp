package starter.search.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Attribute;
import net.serenitybdd.screenplay.questions.SelectedVisibleTextValue;
import net.serenitybdd.screenplay.questions.Value;
import starter.search.targets.MenuAdminTargets;
import java.util.List;

public class MenuAdminQuestions {
    public static Question<String> existingMealName (){
        return Attribute.of(MenuAdminTargets.EDIT_MEAL_NAME)
                .named("placeholder")
                .asAString();
    }

    public static Question<String> existingMealDescription (){
        return Attribute.of(MenuAdminTargets.EDIT_MEAL_DESCRIPTION)
                .named("placeholder")
                .asAString();
    }

    public static Question<String> existingMealPrice (){
        return Attribute.of(MenuAdminTargets.EDIT_MEAL_PRICE)
                .named("placeholder")
                .asAString();
    }

    public static Question<String> existingMealImageUrl (){
        return Attribute.of(MenuAdminTargets.EDIT_MEAL_IMAGE_URL)
                .named("placeholder")
                .asAString();
    }

    public static Question<String> existingMealMealCategory(){
        return SelectedVisibleTextValue.of(MenuAdminTargets.EDIT_MEAL_MEAL_CATEGORY)
                .asAString();
    }

    public static Question<List<String>> existingMealIngredients(){
        return Value.of(MenuAdminTargets.EDIT_MEAL_INGREDIENTS)
                .asAList();
    }
}
