package com.example.backend.model.dto;

import com.example.backend.model.User;
import com.example.backend.model.enumerations.Role;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class UserDetailsDto {
    private String username;
    private Role role;

    public static UserDetailsDto of(User user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.role = user.getUserRole();
        return details;
    }
}



//@Data
//public class UserDetailsDto {
//    private String username;
//    private Role role;
//
//    public static UserDetailsDto of(UserDetails user) {
//        UserDetailsDto details = new UserDetailsDto();
//        details.username = user.getUsername();
//        details.role=Role.CUSTOMER;
////        if(user.getAuthorities().stream().findFirst().equals(Role.CUSTOMER))
////        {
////            details.role =Role.CUSTOMER;
////        }
////        else {
////            details.role=Role.ADMIN;
////        }
//        return details;
//    }
//
//}
