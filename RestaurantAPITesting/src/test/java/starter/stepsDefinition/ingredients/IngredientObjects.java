package starter.stepsDefinition.ingredients;

import starter.data.dto.IngredientDto;
import starter.data.model.Ingredient;

public class IngredientObjects {
    public static IngredientDto ingredientWithId26=new IngredientDto("Lime",45);

    public static Ingredient ingredientWithId1=new Ingredient(1L,80,"Cheese");

    public static Ingredient editedIngredient=new Ingredient(26L,100,"EditedLime");

    public static String addedIngredientName="IngredientTestName";

    public static int addedIngredientQuantity=10;
}
