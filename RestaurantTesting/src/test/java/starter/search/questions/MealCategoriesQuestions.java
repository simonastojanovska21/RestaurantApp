package starter.search.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Attribute;
import net.serenitybdd.screenplay.questions.Text;
import starter.search.targets.MealCategoriesTargets;

public class MealCategoriesQuestions {

    public static Question<String> existingMealCategoryName(){
        return Attribute.of(MealCategoriesTargets.EDIT_MEAL_CATEGORIES_NAME_FIELD)
                .named("placeholder")
                .describedAs("meal category name")
                .asAString();
    }

    public static Question<String> existingMealCategoryImageUrl(){
        return Attribute.of(MealCategoriesTargets.EDIT_MEAL_CATEGORIES_IMAGE_FIELD)
                .named("placeholder")
                .describedAs("meal category imageUrl")
                .asAString();
    }

    public static Question<String> editMealCategoryName(){
        return Text.of(MealCategoriesTargets.EDITED_MEAL_CATEGORY_NAME)
                .describedAs("edited meal category name")
                .asAString();
    }

    public static Question<String> editMealCategoryImageUrl(){
        return Attribute.of(MealCategoriesTargets.EDITED_MEAL_CATEGORY_IMAGE)
                .named("src")
                .describedAs("edited meal category image url")
                .asAString();
    }
}
