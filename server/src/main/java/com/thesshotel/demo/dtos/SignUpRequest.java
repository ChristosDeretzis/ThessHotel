package com.thesshotel.demo.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class SignUpRequest {

    @NotNull
    @ApiModelProperty(notes = "The username of the user", example = "nick780")
    private String username;

    @NotNull
    @Email
    @Length(min = 5, max = 50)
    @ApiModelProperty(notes = "The email of the user", example = "example@gmail.com")
    private String email;

    @NotNull
    @Length(min = 5)
    @ApiModelProperty(notes = "The password of the user", example = "hsgT67^7")
    private String password;
}
