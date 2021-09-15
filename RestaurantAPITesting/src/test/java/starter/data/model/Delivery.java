package starter.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    private Long id;

    private String address;

    private LocalDateTime timeForDelivery;

    private Order orderForDelivery;

}
