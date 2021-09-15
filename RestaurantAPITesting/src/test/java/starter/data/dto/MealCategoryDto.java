package starter.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MealCategoryDto {
    private Long id;
    private String name;
    private String imageUrl;
    private int numberOfMeals;

    public MealCategoryDto(String name, String imageUrl){
        this.name=name;
        this.imageUrl=imageUrl;
    }

    public MealCategoryDto(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
