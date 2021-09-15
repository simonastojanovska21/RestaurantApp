package com.example.backend.service;


import com.example.backend.model.User;
import com.example.backend.model.dto.RegisterDto;
import com.example.backend.model.enumerations.Role;
import com.example.backend.model.exceptions.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> register(RegisterDto registerDto) throws EmptyDataException, MissingFieldException, PasswordsDoNotMatchException, InvalidUsernameException;
    Optional<User> getUserInfo(String username) throws UserNotFoundException;
    User registerUser(String username, String password, String name, String surname, String phoneNumber, String address, Role userRole);
}
