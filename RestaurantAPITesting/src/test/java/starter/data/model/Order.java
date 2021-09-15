package starter.data.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import starter.data.enumerations.OrderStatus;


import java.time.LocalDateTime;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class Order {

    private Long id;

    private LocalDateTime orderedOn;

    private OrderStatus orderStatus;

    private User orderedByUser;


}
