package starter.search.targets;

import net.serenitybdd.screenplay.targets.Target;

public class IngredientsTargets {

    public static Target INGREDIENTS_ROWS=Target.the("ingredients table rows")
            .locatedBy(".ingredientsRow");

    public static Target ADD_INGREDIENT_BUTTON=Target.the("add new ingredient button")
            .locatedBy("#addIngredient");

    public static Target ADD_INGREDIENT_NAME_FIELD=Target.the("ingredient name field")
            .locatedBy("#ingredientName");

    public static Target ADD_INGREDIENT_QUANTITY_FIELD=Target.the("ingredient quantity field")
            .locatedBy("#ingredientQuantity");

    public static Target EDIT_INGREDIENT_NAME_FIELD=Target.the("ingredient name field for edit")
            .locatedBy("#editIngredientName");

    public static Target EDIT_INGREDIENT_QUANTITY_FIELD=Target.the("ingredient quantity field for edit")
            .locatedBy("#editIngredientQuantity");

    public static Target FISH_INGREDIENT_NAME=Target.the("fish name column from ingredient table")
            .locatedBy(".ingredientName-30");

    public static Target FISH_INGREDIENT_QUANTITY=Target.the("fish quantity column from ingredient table")
            .locatedBy(".ingredientQuantity-30");
}
