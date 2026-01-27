package com.muf.modules.authentication.contoroller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String email;
    private String fullName;
    private List<String> roles;
    private Boolean isActive;
    private Boolean isLocked;
}
