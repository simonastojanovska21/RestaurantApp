package starter.stepsDefinition.mealCategories;

import starter.data.dto.MealCategoryDto;
import starter.data.model.MealCategory;

public class MealCategoriesObjects {
    public static MealCategoryDto mealCategoryWithId10=new MealCategoryDto(10L,"Meal category for edit","https://i.pinimg.com/originals/c9/c2/b1/c9c2b12a2b325f0080a1f328a0963341.jpg");

    public static MealCategory mealCategoryWithId1=new MealCategory(1L,"Seafood","https://www.nicepng.com/png/detail/71-715245_salmon-air-fryer-cookbook-the-best-quick-delicious.png");

    public static String addedMealCategoryName="TestMealCategory";

    public static String addedMealCategoryImageUrl="https://www.fitfatherproject.com/wp-content/uploads/2017/09/meal-prep-400.jpg";

    public static MealCategory editedMealCategory=new MealCategory(10L,"EditedMealCategory","https://www.fitfatherproject.com/wp-content/uploads/2017/09/meal-prep-400.jpg");
}
