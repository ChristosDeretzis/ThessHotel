package com.thesshotel.demo.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserDto {

    @ApiModelProperty(notes = "The unique id of the user", example = "34")
    private Integer id;

    @ApiModelProperty(notes = "The username of the user", example = "pako78")
    private String username;

    @ApiModelProperty(notes = "The email of the user", example = "example@gmail.com")
    private String email;

    @ApiModelProperty(notes = "The first name of the user", example = "John")
    private String firstName;

    @ApiModelProperty(notes = "The last name of the user", example = "Smith")
    private String lastName;

    @ApiModelProperty(notes = "The birth date of the user", example = "24/05/96")
    private Date dateOfBirth;

    @ApiModelProperty(notes = "The country of the user", example = "USA")
    private String country;

    @ApiModelProperty(notes = "The city of the user", example = "San Francisco")
    private String city;

    @ApiModelProperty(notes = "The state of the user", example = "California")
    private String state;

    @ApiModelProperty(notes = "The street address of the user", example = "St Helens str")
    private String strAddress;

    @ApiModelProperty(notes = "The street number of the user", example = "45")
    private Integer strNumber;

    @ApiModelProperty(notes = "The zip code of the user", example = "45624")
    private Integer zipCode;

    private List<String> roles = new ArrayList<>();
}
