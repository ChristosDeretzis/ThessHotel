package com.thesshotel.demo.dtos;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserDto {

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String country;
    private String city;
    private String state;
    private String strAddress;
    private Integer strNumber;
    private Integer zipCode;
}
