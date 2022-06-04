package com.thesshotel.demo.services;

import com.thesshotel.demo.Utils.DtoModel.DtoToModel;
import com.thesshotel.demo.Utils.DtoModel.ModelToDto;
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

    public UserDto updateUser(UserDto newUser, Integer id) {

        return userRepository.findById(id)
                .map(user -> {
                    User updateUser = DtoToModel.convertUserDtoToModel(newUser);
                    updateUser.setId(id);
                    userRepository.save(updateUser);
                    return newUser;
                })
                .orElseThrow(() -> new NotFoundException("User was not found"));
    }

    public UserDto getUser(Integer id) {
        // Get user based on bearer token
        return userRepository.findById(id)
                .map(user -> ModelToDto.convertUserModelToDto(user))
                .orElseThrow(() -> new NotFoundException("User was not found"));
    }

    public UserDto deleteUser(Integer id) {
        // Get user based on bearer token

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User was not found"));

        userRepository.delete(user);
        return ModelToDto.convertUserModelToDto(user);
    }
}
