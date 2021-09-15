package starter.search.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;
import starter.search.targets.MealCategoriesTargets;

public class PerformActionsForMealCategories {

    public static Performable addNewMealCategory(String name, String imageUrl){
        return Task.where("{0} attempts to add new meal category",
                Enter.theValue(name)
                .into(MealCategoriesTargets.ADD_MEAL_CATEGORIES_NAME_FIELD),
                Enter.theValue(imageUrl)
                .into(MealCategoriesTargets.ADD_MEAL_CATEGORIES_IMAGE_FIELD)
                .thenHit(Keys.ENTER)  );
    }

    public static Performable editMealCategoryName(String name){
        return Task.where("{0} attempts to edit meal category",
                Enter.theValue(name)
                        .into(MealCategoriesTargets.EDIT_MEAL_CATEGORIES_NAME_FIELD)
                        .thenHit(Keys.ENTER)  );
    }

    public static Performable editMealCategoryImageUrl(String imageUrl){
        return Task.where("{0} attempts to edit meal category",
                Enter.theValue(imageUrl)
                        .into(MealCategoriesTargets.EDIT_MEAL_CATEGORIES_IMAGE_FIELD)
                        .thenHit(Keys.ENTER)  );
    }
}
