package com.thesshotel.demo.services;

import com.thesshotel.demo.Utils.DtoModel.DtoToModel;
import com.thesshotel.demo.Utils.DtoModel.ModelToDto;
import com.thesshotel.demo.dtos.UserDto;
import com.thesshotel.demo.exceptions.NotFoundException;
import com.thesshotel.demo.models.Role;
import com.thesshotel.demo.models.User;
import com.thesshotel.demo.repositories.RoleRepository;
import com.thesshotel.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserDto updateUser(UserDto newUser, Integer id) {

        return userRepository.findById(id)
                .map(user -> {
                    User updateUser = DtoToModel.convertUserDtoToModel(newUser);

                    // Set user roles
                    Set<Role> userRoles = newUser.getRoles()
                            .stream()
                            .map(roleName -> {
                                Role role = roleRepository.findByName(roleName);
                                return role;
                            }).collect(Collectors.toSet());

                    updateUser.setRoles(userRoles);
                    updateUser.setPassword(user.getPassword());
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
