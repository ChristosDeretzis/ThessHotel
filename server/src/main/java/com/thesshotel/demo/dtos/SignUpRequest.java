package com.thesshotel.demo.dtos;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
