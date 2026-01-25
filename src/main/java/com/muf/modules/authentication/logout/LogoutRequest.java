package com.muf.modules.authentication.logout;

import java.io.Serializable;

public class LogoutRequest implements Serializable {
    private String token;

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
