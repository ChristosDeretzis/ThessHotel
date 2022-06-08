package com.thesshotel.demo.Utils.DtoModel;

import com.thesshotel.demo.dtos.UserDto;
import com.thesshotel.demo.models.User;

public class ModelToDto {

    public static UserDto convertUserModelToDto(User user) {
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
                .build();

    }
}
