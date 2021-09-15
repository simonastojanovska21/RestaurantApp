package starter.data.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import starter.data.enumerations.Role;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  {

    private String username;

    private String password;

    private String name;

    private String surname;

    private String phoneNumber;

    private String address;

    private Role userRole;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    private String[] authorities;


}
