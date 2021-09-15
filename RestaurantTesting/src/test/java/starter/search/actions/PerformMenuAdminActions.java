package starter.search.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import org.jruby.javasupport.Java;
import starter.search.targets.MenuAdminTargets;

public class PerformMenuAdminActions {

    public static Performable addNewMeal(String name, String description, String price, String category, String imageUrl){
        return Task.where("{0} attempts to add new meal",
                Enter.theValue(name)
                .into(MenuAdminTargets.ADD_MEAL_NAME),
                Enter.theValue(description)
                .into(MenuAdminTargets.ADD_MEAL_DESCRIPTION),
                Enter.theValue(price)
                .into(MenuAdminTargets.ADD_MEAL_PRICE),
                SelectFromOptions.byVisibleText(category).from(MenuAdminTargets.ADD_MEAL_CATEGORIES_SELECT_LIST),
                Enter.theValue(imageUrl)
                .into(MenuAdminTargets.ADD_MEAL_IMAGE_URL),
                JavaScriptClick.on(MenuAdminTargets.ADD_MEAL_INGREDIENT8),
                JavaScriptClick.on(MenuAdminTargets.ADD_MEAL_INGREDIENT9),
                JavaScriptClick.on(MenuAdminTargets.ADD_MEAL_INGREDIENT10),
                JavaScriptClick.on(MenuAdminTargets.SUBMIT_ADD_MEAL)
                );
    }

    public static Performable editMealName  (String name){
        return Task.where("{0} attempts to edit meal",
                Enter.theValue(name)
                .into(MenuAdminTargets.EDIT_MEAL_NAME),
                JavaScriptClick.on(MenuAdminTargets.SUBMIT_EDIT_MEAL)
                );
    }

    public static Performable editMealDescription (String description){
        return Task.where("{0} attempts to edit meal",
                Enter.theValue(description)
                        .into(MenuAdminTargets.EDIT_MEAL_DESCRIPTION),
                JavaScriptClick.on(MenuAdminTargets.SUBMIT_EDIT_MEAL)
        );
    }

    public static Performable editMealPrice  (String price){
        return Task.where("{0} attempts to edit meal",
                Enter.theValue(price)
                        .into(MenuAdminTargets.EDIT_MEAL_PRICE),
                JavaScriptClick.on(MenuAdminTargets.SUBMIT_EDIT_MEAL)
        );
    }

    public static Performable editMealImageUrl  (String imageUrl){
        return Task.where("{0} attempts to edit meal",
                Enter.theValue(imageUrl)
                        .into(MenuAdminTargets.EDIT_MEAL_IMAGE_URL),
                JavaScriptClick.on(MenuAdminTargets.SUBMIT_EDIT_MEAL)
        );
    }

    public static Performable editMealCategory  (String category){
        return Task.where("{0} attempts to edit meal",
                SelectFromOptions.byVisibleText(category).from(MenuAdminTargets.EDIT_MEAL_MEAL_CATEGORY),
                JavaScriptClick.on(MenuAdminTargets.SUBMIT_EDIT_MEAL)
        );
    }

    public static Performable editMealIngredients (){
        return Task.where("{0} attempts to edit meal",
                JavaScriptClick.on(MenuAdminTargets.ADD_MEAL_INGREDIENT9),
                JavaScriptClick.on(MenuAdminTargets.ADD_MEAL_INGREDIENT10),
                JavaScriptClick.on(MenuAdminTargets.ADD_MEAL_INGREDIENT12),
                JavaScriptClick.on(MenuAdminTargets.ADD_MEAL_INGREDIENT13),
                JavaScriptClick.on(MenuAdminTargets.SUBMIT_EDIT_MEAL)
        );
    }
}
