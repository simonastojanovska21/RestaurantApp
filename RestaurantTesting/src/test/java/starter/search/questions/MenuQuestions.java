package starter.search.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Value;
import starter.search.targets.MealTargets;
import starter.search.targets.MenuTargets;

import java.util.List;

public class MenuQuestions {

    public static Question<List<String>> displayedMealName(){
        return Text.of(MenuTargets.MENU_ITEMS_NAME)
                .asAList();
    }

    public static Question<String> Meal3QuantityFieldValue(){
        return Value.of(MenuTargets.MEAL3_QUANTITY_FIELD)
                .describedAs("quantity field for meal3")
                .asAString();
    }
}
