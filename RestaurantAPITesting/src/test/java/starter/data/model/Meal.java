package starter.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

    private Long id;

    private String name;

    private String description;

    private double price;

    private MealCategory mealCategory;

    private String imageUrl;

    private List<Ingredient> ingredientsForMeal;

}
