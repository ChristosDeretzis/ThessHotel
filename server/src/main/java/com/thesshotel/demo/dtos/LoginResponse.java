package com.thesshotel.demo.dtos;

public class LoginResponse {

    private String email;
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
