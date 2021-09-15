package starter.search.targets;

import net.serenitybdd.screenplay.targets.Target;

public class MenuAdminTargets {

    public static Target ADD_MEAL_TO_MENU=Target.the("add meal to menu button")
            .locatedBy("#addMealToMenu");

    public static Target ADD_MEAL_NAME=Target.the("add meal")
            .locatedBy("#mealName");
    public static Target ADD_MEAL_DESCRIPTION=Target.the("add meal")
            .locatedBy("#mealDescription");
    public static Target ADD_MEAL_PRICE=Target.the("add meal")
            .locatedBy("#mealPrice");
    public static Target ADD_MEAL_CATEGORIES_SELECT_LIST=Target.the("add meal")
            .locatedBy("#mealMealCategory");
    public static Target ADD_MEAL_IMAGE_URL=Target.the("add meal")
            .locatedBy("#mealImageUrl");

    public static Target ADD_MEAL_INGREDIENT8=Target.the("ingredient")
            .locatedBy(".ingredient-8");
    public static Target ADD_MEAL_INGREDIENT9=Target.the("ingredient")
            .locatedBy(".ingredient-9");
    public static Target ADD_MEAL_INGREDIENT10=Target.the("ingredient")
            .locatedBy(".ingredient-10");
    public static Target ADD_MEAL_INGREDIENT12=Target.the("ingredient")
            .locatedBy(".ingredient-12");
    public static Target ADD_MEAL_INGREDIENT13=Target.the("ingredient")
            .locatedBy(".ingredient-13");
    public static Target SUBMIT_ADD_MEAL=Target.the("submit")
            .locatedBy("#addMealSubmit");

    public static Target EDIT_MEAL_NAME =Target.the("edit meal")
            .locatedBy("#editMealName");
    public static Target EDIT_MEAL_DESCRIPTION =Target.the("edit meal")
            .locatedBy("#editMealDescription");
    public static Target EDIT_MEAL_PRICE =Target.the("edit meal")
            .locatedBy("#editMealPrice");
    public static Target EDIT_MEAL_IMAGE_URL =Target.the("edit meal")
            .locatedBy("#editMealImageUrl");
    public static Target EDIT_MEAL_MEAL_CATEGORY=Target.the("edit meal")
            .locatedBy("#editMealMealCategory");
    public static Target EDIT_MEAL_INGREDIENTS=Target.the("edit meal")
            .locatedBy("#editIngredientsForMeal li input:checked");
    public static Target SUBMIT_EDIT_MEAL=Target.the("submit")
            .locatedBy("#editMealSubmit");
}
