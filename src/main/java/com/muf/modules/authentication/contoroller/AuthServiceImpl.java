package com.muf.modules.authentication.contoroller;

import com.muf.infrastructure.jwt.JwtUtil;
import com.muf.modules.authentication.login.LoginRequest;
import com.muf.modules.authentication.mapper.AuthMapper;
import com.muf.modules.authentication.register.RegisterRequest;
import com.muf.modules.master.user.entity.domain.RefreshToken;
import com.muf.modules.master.user.entity.domain.Role;
import com.muf.modules.master.user.entity.domain.User;
import com.muf.modules.master.user.entity.domain.UserRole;
import com.muf.modules.master.user.repository.RefreshTokenRepository;
import com.muf.modules.master.user.repository.RoleRepository;
import com.muf.modules.master.user.repository.UserRepository;
import com.muf.modules.master.user.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthMapper authMapper;

//    private static final int MAX_LOGIN_ATTEMPTS = 5;

    // =========================
    // REGISTER
    // =========================
    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {

        // 1. validation
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Password and confirm password do not match");
        }

        // 2. create user
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setLoginAttempt(0);
        user.setUsed(false);
        user.setCreatedAt(new Date());

        userRepository.save(user);

        // 3. resolve role
        Role role;

        if (request.getRoleId() != null) {
            role = roleRepository.findById(request.getRoleId().intValue())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
        } else {
            role = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
        }

        // 4. assign role
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setStartDate(new Date());
        userRole.setCreatedAt(new Date());

        user.addRole(userRole);
        userRoleRepository.save(userRole);

        // 5. reload user with roles
        User fullUser = userRepository.findByIdWithRoles(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found after register"));

        // 6. generate tokens
        String accessToken = jwtUtil.generateToken(fullUser.getEmail());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(fullUser);

        // 7. response
        return authMapper.toAuthResponse(fullUser, accessToken, refreshToken.getToken());
    }

    // =========================
    // LOGIN
    // =========================


    @Override
    @Transactional
    public AuthResponse login(LoginRequest request) {

        // 1. find user
        User user = userRepository.findByEmailWithRoles(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // 2. validate password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            user.setLoginAttempt(user.getLoginAttempt() + 1);
            user.setLoginAttemptDate(new Date());
            user.setUpdatedAt(new Date());
            userRepository.save(user);

            throw new RuntimeException("Invalid credentials");
        }

        // 3. reset login metadata
        user.setLoginAttempt(0);
        user.setLoginAttemptDate(null);
        user.setLogDate(new Date());
        user.setUpdatedAt(new Date());

        userRepository.save(user);

        // 4. read rememberMe
        boolean rememberMe = Boolean.TRUE.equals(request.getRememberMe());

        // 4. generate tokens
        String accessToken = jwtUtil.generateToken(user.getEmail());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        // 5. response
        return authMapper.toAuthResponse(user, accessToken, refreshToken.getToken());
    }


    // =========================
    // REFRESH TOKEN
    // =========================
    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        refreshTokenService.verifyExpiration(refreshToken);

        User user = userRepository.findByIdWithRoles(refreshToken.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = jwtUtil.generateToken(user.getEmail());

        return authMapper.toAuthResponse(user, accessToken, refreshToken.getToken());
    }

    // =========================
    // LOGOUT
    // =========================
    @Override
    public void logout(String email) {
        User user = userRepository.findByEmailWithRoles(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        refreshTokenService.deleteByUser(user);
    }

}
