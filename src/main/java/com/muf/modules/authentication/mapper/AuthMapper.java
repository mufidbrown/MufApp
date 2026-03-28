package com.muf.modules.authentication.mapper;

import com.muf.modules.authentication.contoroller.AuthResponse;
import com.muf.modules.master.user.entity.domain.Role;
import com.muf.modules.master.user.entity.domain.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthMapper {

    public AuthResponse toAuthResponse(User user, String accessToken, String refreshToken) {

        AuthResponse response = new AuthResponse();

        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());

        response.setRoles(
                user.getUserRoleList()
                        .stream()
                        .map(userRole -> userRole.getRole().getName())
                        .toList()
        );

        response.setIsActive(user.getIsActive());
        response.setIsLocked(user.getIsLocked());

        return response;
    }
}
