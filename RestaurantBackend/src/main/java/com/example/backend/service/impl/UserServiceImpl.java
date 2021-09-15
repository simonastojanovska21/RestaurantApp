package com.example.backend.service.impl;

import com.example.backend.model.User;
import com.example.backend.model.dto.RegisterDto;
import com.example.backend.model.enumerations.Role;
import com.example.backend.model.exceptions.*;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> register(RegisterDto registerDto) throws EmptyDataException, MissingFieldException, PasswordsDoNotMatchException, InvalidUsernameException {
        if(registerDto.getName()==null || registerDto.getSurname()==null || registerDto.getUsername()==null || registerDto.getAddress()==null
                || registerDto.getPassword()==null || registerDto.getRepeatedPassword()==null || registerDto.getPhoneNumber()==null)
            throw new MissingFieldException();

        if(registerDto.getName().isEmpty() || registerDto.getSurname().isEmpty() || registerDto.getUsername().isEmpty() || registerDto.getAddress().isEmpty()
                || registerDto.getPassword().isEmpty() || registerDto.getRepeatedPassword().isEmpty() || registerDto.getPhoneNumber().isEmpty())
            throw new EmptyDataException();

        if(!registerDto.getPassword().equals(registerDto.getRepeatedPassword()))
            throw new PasswordsDoNotMatchException("Passwords do not match exception");

        if(this.userRepository.findByUsername(registerDto.getUsername()).isPresent())
            throw new InvalidUsernameException("User with that username already exists");

        String encrypted=this.passwordEncoder.encode(registerDto.getPassword());
        User user=new User(registerDto.getUsername(),encrypted,registerDto.getName(),registerDto.getSurname()
                ,registerDto.getPhoneNumber(),registerDto.getAddress(),Role.ROLE_CUSTOMER);
        return Optional.of(this.userRepository.save(user));
    }

    @Override
    public Optional<User> getUserInfo(String username) throws UserNotFoundException {
        Optional<User> user=this.userRepository.findByUsername(username);
        if(user.isEmpty())
            throw new UserNotFoundException();
        return user;
    }

    @Override
    public User registerUser(String username, String password, String name, String surname, String phoneNumber, String address, Role userRole) {
        String encrypted=this.passwordEncoder.encode(password);
        User user=new User(username,encrypted,name,surname,phoneNumber,address,userRole);
        return this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }


}
