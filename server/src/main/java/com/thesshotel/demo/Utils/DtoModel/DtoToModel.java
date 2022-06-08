package com.thesshotel.demo.Utils.DtoModel;

import com.thesshotel.demo.dtos.UserDto;
import com.thesshotel.demo.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DtoToModel {

    public static User convertUserDtoToModel(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
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
