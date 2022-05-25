package com.thesshotel.demo.dtos;

import io.swagger.annotations.ApiModelProperty;

public class LoginResponse {

    @ApiModelProperty(notes = "The email of the user", example = "example@gmail.com")
    private String email;

    @ApiModelProperty(notes = "The access Token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJudWxsLGV4YW1wbGVAZ21haWwuY29tIiwiaXNzIjoiQ29kZUphdmEiLCJpYXQiOjE2NTM0ODAxNzAsImV4cCI6MTY1MzU2NjU3MH0.u29j_1v2xxhtpEaf-vEq-aPI_UHm3vQZMlG0jpAd3uThQ0vK-VxraQQDgITXhtvWTIhN59vBCUfEV1fsTLvMQw")
    private String accessToken;

    public LoginResponse(String email, String token) {
        this.email = email;
        this.accessToken = token;
    }

    public LoginResponse() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
