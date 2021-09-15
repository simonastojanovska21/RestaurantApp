package starter.search.targets;

import net.serenitybdd.screenplay.targets.Target;

public class MealCategoriesTargets {
    public static Target MEAL_CATEGORIES_ROWS=Target.the("meal categories table rows")
            .locatedBy(".mealCategoriesRow");

    public static Target ADD_MEAL_CATEGORIES_BUTTON=Target.the("add new meal category button")
            .locatedBy("#addMealCategory");

    public static Target ADD_MEAL_CATEGORIES_NAME_FIELD=Target.the("meal category name field")
            .locatedBy("#mealCategoryName");

    public static Target ADD_MEAL_CATEGORIES_IMAGE_FIELD=Target.the("meal category imageUrl field")
            .locatedBy("#mealCategoryImageUrl");

    public static Target EDIT_MEAL_CATEGORIES_NAME_FIELD=Target.the("meal category name field for edit")
            .locatedBy("#editMealCategoryName");

    public static Target EDIT_MEAL_CATEGORIES_IMAGE_FIELD=Target.the("meal category imageUrl field for edit")
            .locatedBy("#editMealCategoryImageUrl");

    public static Target EDITED_MEAL_CATEGORY_NAME=Target.the("edited meal category name column from meal category table")
            .locatedBy(".mealCategoryName-10");

    public static Target EDITED_MEAL_CATEGORY_IMAGE=Target.the("edited meal category imageUrl from meal category table")
            .locatedBy(".mealCategoryImage-10");
}
