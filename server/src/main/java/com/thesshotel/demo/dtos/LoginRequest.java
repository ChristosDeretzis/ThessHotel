package com.thesshotel.demo.dtos;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class LoginRequest {

    @NotNull
    @Email
    @Length(min = 5)
    @ApiModelProperty(notes = "The email of the user", example = "example@gmail.com")
    private String email;

    @NotNull
    @ApiModelProperty(notes = "The password of the user", example = "Reey&7653")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
