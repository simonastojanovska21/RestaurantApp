package starter.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class OrderItem {

    private Long id;

    private int quantity;

    private Order itemInOrder;

    private Meal orderItemForMeal;

}
