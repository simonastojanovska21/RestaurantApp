package starter.search.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;
import starter.search.targets.IngredientsTargets;

public class PerformActionsForIngredients {

    public static Performable addNewIngredient(String name, String quantity){
        return Task.where("{0} tries to add new ingredient",
                Enter.theValue(name)
                    .into(IngredientsTargets.ADD_INGREDIENT_NAME_FIELD),
                Enter.theValue(quantity)
                    .into(IngredientsTargets.ADD_INGREDIENT_QUANTITY_FIELD)
                .thenHit(Keys.ENTER)
        );
    }

    public static Performable editIngredientName(String name){
        return Task.where("{0} tries to edit ingredient name",
                Enter.theValue(name)
                        .into(IngredientsTargets.EDIT_INGREDIENT_NAME_FIELD)
                        .thenHit(Keys.ENTER)
        );
    }

    public static Performable editIngredientQuantity(String quantity){
        return Task.where("{0} tries to edit ingredient quantity",
                Enter.theValue(quantity)
                        .into(IngredientsTargets.EDIT_INGREDIENT_QUANTITY_FIELD)
                        .thenHit(Keys.ENTER)
        );
    }
}
