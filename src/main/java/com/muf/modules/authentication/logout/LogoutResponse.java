package com.muf.modules.authentication.logout;

import java.util.Date;

public class LogoutResponse {

    private String message;
    private Date logoutAt;

    public LogoutResponse() {
        this.message = "Logout success";
        this.logoutAt = new Date();
    }

    public String getMessage() {
        return message;
    }

    public Date getLogoutAt() {
        return logoutAt;
    }
}

