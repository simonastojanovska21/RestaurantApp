package starter.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private Long id;

    private int stars;

    private String description;

    private LocalDateTime localDateTime;

    private User user;
}
