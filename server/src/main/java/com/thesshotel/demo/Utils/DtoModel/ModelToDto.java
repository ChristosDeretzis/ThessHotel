package com.thesshotel.demo.Utils.DtoModel;

import com.thesshotel.demo.dtos.UserDto;
import com.thesshotel.demo.models.User;

import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelToDto {

    public static UserDto convertUserModelToDto(User user) {
        List<String> roles = user.getRoles()
                                    .stream()
                                    .map(role -> role.getName())
                                    .collect(Collectors.toList());
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .city(user.getCity())
                .country(user.getCountry())
                .state(user.getState())
                .strAddress(user.getStrAddress())
                .strNumber(user.getStrNumber())
                .zipCode(user.getZipCode())
                .roles(roles)
                .build();

    }
}
