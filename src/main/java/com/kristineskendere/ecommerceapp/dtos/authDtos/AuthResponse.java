package com.kristineskendere.ecommerceapp.dtos.authDtos;

import com.kristineskendere.ecommerceapp.models.authModels.User;

public class AuthResponse {

    private User user;
    private String accessToken;

    public AuthResponse() {
    }

    public AuthResponse(User user, String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
