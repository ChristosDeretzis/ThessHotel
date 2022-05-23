package com.thesshotel.demo.dtos;

public class SignUpResponse {

    private String email;
    private String username;
    private String accessToken;

    public SignUpResponse(String email, String username, String token) {
        this.email = email;
        this.username = username;
        this.accessToken = token;
    }

    public SignUpResponse() {
    }

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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
