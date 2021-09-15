package starter.data.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MealDto {
    private String name;

    private String description;

    private double price;

    private Long mealCategory;

    private String imageUrl;

    private List<Long> ingredientsForMeal;
}
