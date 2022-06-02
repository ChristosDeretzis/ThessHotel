package com.thesshotel.demo.Utils;

import com.thesshotel.demo.dtos.UserDto;
import com.thesshotel.demo.models.User;

public class ModelToDto {

    public static UserDto convertUserDtoToModel(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .city(user.getCity())
                .country(user.getCountry())
                .state(user.getState())
                .strAddress(user.getStrAddress())
                .strNumber(user.getStrNumber())
                .zipCode(user.getZipCode())
                .build();

    }
}
