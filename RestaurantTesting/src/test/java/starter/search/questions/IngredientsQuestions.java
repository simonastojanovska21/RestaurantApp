package starter.search.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Attribute;
import net.serenitybdd.screenplay.questions.Text;
import starter.search.targets.IngredientsTargets;

public class IngredientsQuestions {

    public static Question<String> existingIngredientName()
    {
        return Attribute.of(IngredientsTargets.EDIT_INGREDIENT_NAME_FIELD)
                .named("placeholder")
                .describedAs("ingredient name")
                .asAString();
    }

    public static Question<String> existingIngredientQuantity()
    {
        return Attribute.of(IngredientsTargets.EDIT_INGREDIENT_QUANTITY_FIELD)
                .named("placeholder")
                .describedAs("ingredient quantity")
                .asAString();
    }

    public static Question<String> fishIngredientName(){
        return Text.of(IngredientsTargets.FISH_INGREDIENT_NAME)
                .describedAs("fish ingredient name")
                .asAString();
    }

    public static Question<String> fishIngredientQuantity(){
        return Text.of(IngredientsTargets.FISH_INGREDIENT_QUANTITY)
                .describedAs("fish ingredient quantity")
                .asAString();
    }
}
