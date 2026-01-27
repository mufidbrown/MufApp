package com.muf.modules.authentication.contoroller;

import com.muf.modules.authentication.login.LoginRequest;
import com.muf.modules.authentication.register.RegisterRequest;


public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
    void logout(String email);
}
