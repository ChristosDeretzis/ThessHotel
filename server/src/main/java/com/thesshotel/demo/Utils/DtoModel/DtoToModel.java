package com.thesshotel.demo.Utils.DtoModel;

import com.thesshotel.demo.dtos.UserDto;
import com.thesshotel.demo.models.User;

public class DtoToModel {

    public static User convertUserDtoToModel(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .dateOfBirth(userDto.getDateOfBirth())
                .city(userDto.getCity())
                .country(userDto.getCountry())
                .state(userDto.getState())
                .strAddress(userDto.getStrAddress())
                .strNumber(userDto.getStrNumber())
                .zipCode(userDto.getZipCode())
                .build();

    }
}
