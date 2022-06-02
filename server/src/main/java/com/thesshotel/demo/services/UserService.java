package com.thesshotel.demo.services;

import com.thesshotel.demo.Utils.ModelToDto;
import com.thesshotel.demo.dtos.UserDto;
import com.thesshotel.demo.exceptions.NotFoundException;
import com.thesshotel.demo.models.User;
import com.thesshotel.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto updateUser(UserDto newUser) {
        // Get user based on bearer token
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = currentUser.getId();

        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setEmail(newUser.getEmail());
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setDateOfBirth(newUser.getDateOfBirth());
                    user.setCountry(newUser.getCountry());
                    user.setState(newUser.getState());
                    user.setCity(newUser.getCity());
                    user.setStrAddress(newUser.getStrAddress());
                    user.setStrNumber(newUser.getStrNumber());
                    userRepository.save(user);
                    return newUser;
                })
                .orElseThrow(() -> new NotFoundException("User was not found"));
    }

    public UserDto getUser() {
        // Get user based on bearer token
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = currentUser.getId();

        return userRepository.findById(id)
                .map(user -> ModelToDto.convertUserDtoToModel(user))
                .orElseThrow(() -> new NotFoundException("User was not found"));
    }

    public UserDto deleteUser() {
        // Get user based on bearer token
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = currentUser.getId();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User was not found"));

        userRepository.delete(user);
        return ModelToDto.convertUserDtoToModel(user);
    }
}
