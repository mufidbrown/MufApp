package com.muf.modules.authentication.contoroller;

import com.muf.infrastructure.security.jwt.JwtUtil;
import com.muf.modules.authentication.login.LoginRequest;
import com.muf.modules.authentication.register.RegisterRequest;
import com.muf.modules.user.entity.domain.RefreshToken;
import com.muf.modules.user.entity.domain.Role;
import com.muf.modules.user.entity.domain.User;
import com.muf.modules.user.entity.domain.UserRole;
import com.muf.modules.user.repository.RefreshTokenRepository;
import com.muf.modules.user.repository.RoleRepository;
import com.muf.modules.user.repository.UserRepository;
import com.muf.modules.user.repository.UserRoleRepository;
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

    private static final int MAX_LOGIN_ATTEMPTS = 5;

    // =========================
    // REGISTER
    // =========================
    @Override
    public AuthResponse register(RegisterRequest request) {

        validateRegisterRequest(request);

        User user = createUser(request);
        userRepository.save(user);

        Role role = resolveRole(request);
        assignRole(user, role);

        // IMPORTANT: reload user with roles
        User fullUser = userRepository.findByIdWithRoles(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found after register"));

        return createAuthResponse(fullUser);
    }

    // =========================
    // LOGIN
    // =========================


    @Override
    @Transactional
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmailWithRoles(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            handleFailedLogin(user);
            throw new RuntimeException("Invalid credentials");
        }

        // update login metadata saja
        user.setLoginAttempt(0);
        user.setLoginAttemptDate(null);
        user.setLogDate(new Date());
        user.setUpdatedAt(new Date());

        userRepository.save(user); // ✅ AMAN: tidak sentuh user_role

        return createAuthResponse(user);
    }


    // =========================
    // REFRESH TOKEN
    // =========================
    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenService.verifyExpiration(
                refreshTokenRepository.findByToken(request.getRefreshToken())
                        .orElseThrow(() -> new RuntimeException("Invalid refresh token"))
        );

        User user = userRepository.findByIdWithRoles(refreshToken.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = jwtUtil.generateToken(user.getEmail());

        AuthResponse response = new AuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken.getToken());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setRoles(extractRoles(user));

        return response;
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

    // ======================================================
    // PRIVATE HELPERS
    // ======================================================

    private void validateRegisterRequest(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Password and confirm password do not match");
        }
    }

    private User createUser(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setLoginAttempt(0);
        user.setUsed(false);
        user.setCreatedAt(new Date());
        return user;
    }

    private Role resolveRole(RegisterRequest request) {
        if (request.getRoleId() != null) {
            return roleRepository.findById(request.getRoleId().intValue())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
        }
        return roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
    }

    private void assignRole(User user, Role role) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setStartDate(new Date());
        userRole.setCreatedAt(new Date());
        user.addRole(userRole);
        userRoleRepository.save(userRole);
    }

    private void handleFailedLogin(User user) {
        user.setLoginAttempt(user.getLoginAttempt() + 1);
        user.setLoginAttemptDate(new Date());
        user.setUpdatedAt(new Date());

//        if (user.getLoginAttempt() >= MAX_LOGIN_ATTEMPTS) {
//            user.setIsLocked(true);
//        }

        userRepository.save(user);
    }

    private void resetLoginAttempt(User user) {
        user.setLoginAttempt(0);
        user.setLoginAttemptDate(null);
        user.setLogDate(new Date());
        user.setUpdatedAt(new Date());
        userRepository.save(user);
    }

    private AuthResponse createAuthResponse(User user) {

        String accessToken = jwtUtil.generateToken(user.getEmail());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        List<String> roles = user.getUserRoleList() == null
                ? List.of()
                : user.getUserRoleList().stream()
                .map(ur -> ur.getRole().getCode())
                .toList();

        AuthResponse response = new AuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken.getToken());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setRoles(roles);
        response.setIsActive(user.getActive());
        response.setIsLocked(user.getLocked());

        return response;
    }


    private List<String> extractRoles(User user) {
        if (user.getUserRoleList() == null || user.getUserRoleList().isEmpty()) {
            return new ArrayList<>();
        }
        return user.getUserRoleList().stream()
                .map(ur -> ur.getRole().getName())
                .collect(Collectors.toList());
    }
}
