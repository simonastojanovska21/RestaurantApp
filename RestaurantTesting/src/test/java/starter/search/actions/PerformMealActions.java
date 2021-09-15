package starter.search.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import starter.search.targets.MealTargets;

public class PerformMealActions {

    public static Performable setUpQuantityFieldForMeal2(String quantity){
        return Task.where("{0} sees the value",
                Enter.theValue(quantity)
                .into(MealTargets.MEAL2_QUANTITY_FIELD)
                );
    }
}
