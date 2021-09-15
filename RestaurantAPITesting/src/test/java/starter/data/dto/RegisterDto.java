package starter.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    private String username;

    private String password;

    private String repeatedPassword;

    private String name;

    private String surname;

    private String phoneNumber;

    private String address;

}
