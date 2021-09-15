package starter.stepsDefinition.meals;

import starter.data.dto.MealDto;
import starter.data.model.Ingredient;
import starter.data.model.Meal;
import starter.data.model.MealCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealsObjects {

    public static MealDto mealWithId2=new MealDto("Meal for edit","Meal for edit description",122,2L,"https://food-guide.canada.ca/sites/default/files/styles/square_400_x_400/public/2020-12/CFGPlate-crop400x400.jpg", Arrays.asList(5L,6L,7L,8L));


    public static Ingredient ingredient1=new Ingredient(1L,80,"Cheese");
    public static Ingredient ingredient16=new Ingredient(16L,25,"Ketchup");
    public static Ingredient ingredient24=new Ingredient(24L,85,"Spaghetti");
    public static Ingredient ingredient15=new Ingredient(15L,85,"Bacon");

    public static List<Ingredient> ingredientList;

    static {
        ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient15);
        ingredientList.add(ingredient16);
        ingredientList.add(ingredient24);
    }

    public static MealCategory mealCategory=new MealCategory(7L,"Pasta","https://i.pinimg.com/originals/ae/ce/cf/aececf995c0549a49e82e38a1bb3a586.jpg");
    public static Meal mealWithId3=new Meal(3L,"Meal1","Meal1 description",50,mealCategory,"https://www.bosscaffe.com/sites/default/files/styles/product_thumb/public/2019-04/PASTA_BOLOGNESE.jpg?itok=xlPQ0A54",ingredientList);

    public static String addedMealName="TestMealName";
    public static String addedMealDescription="Test meal description";
    public static double addedMealPrice=200;
    public static Long addedMealMealCategory=9L;
    public static String addedMealImageUrl="https://i.pinimg.com/474x/99/c6/71/99c6716be635fdb4c28cdfce5f15adb6.jpg";
    public static List<Long> addedMealIngredientsId=Arrays.asList(8L,9L,10L);

    public static MealCategory editedMealMealCategory=new MealCategory(2L,"Pizza","https://hips.hearstapps.com/wdy.h-cdn.co/assets/cm/15/09/54ef8b64366c1_-_fresh-tomato-pizza-kqgv52-xl.jpg");

    public static Ingredient ingredient9=new Ingredient(9L,87,"Chicken");
    public static Ingredient ingredient10=new Ingredient(10L,45,"Croutons");
    public static Ingredient ingredient12=new Ingredient(12L,122,"Corn");
    public static Ingredient ingredient13=new Ingredient(13L,45,"Cucumber");





    public static List<Ingredient> editedMealIngredientList;

    static {
        editedMealIngredientList = new ArrayList<>();
        editedMealIngredientList.add(ingredient9);
        editedMealIngredientList.add(ingredient10);
        editedMealIngredientList.add(ingredient12);
        editedMealIngredientList.add(ingredient13);
    }
    public static Meal editedMeal=
            new Meal(2L,"Edited meal","Edited meal description",35,editedMealMealCategory,"https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15534.jpg?ext=.jpg",editedMealIngredientList);


}
