package com.muf.core.session;


import com.muf.model.UserSession;
import com.muf.infrastructure.security.jwt.JwtUtil;
import com.muf.modules.user.entity.domain.User;
import com.muf.modules.user.entity.domain.UserRole;
import com.muf.modules.user.repository.UserRepository;
import com.muf.modules.user.repository.UserRoleRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class CurrentUserSession {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final HttpServletRequest request;

    public CurrentUserSession(HttpServletRequest request, JwtUtil jwtUtil, UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.request = request;
        this.jwtUtil = jwtUtil;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }

    private final JwtUtil jwtUtil;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
//    private final UserBeanRepository userBeanRepository;
//    private final UserBeanRepository userBeanRepository;
//    private final VendorBeanRepository vendorBeanRepository;


    public UserSession getUserSession() {
        String auth = request.getHeader("Authorization");
        if (auth == null || !auth.startsWith("Bearer ")) return null;

        String token = auth.substring(7);
        Claims claims = jwtUtil.parseToken(token);

        Integer userId = Integer.valueOf(claims.getSubject());
        String roleCode = claims.get("role", String.class);

        User user = userRepository.getById(userId);
        if (user == null) return null;

        UserSession userSession = new UserSession();
        userSession.setUser(user);
//        userSession.setUserBean(userBeanRepository.getById(userId));
        userSession.setCurrentLangCode(user.getLang().getCode());
        List<UserRole> roles = userRoleRepository.findByUserIdAndRoleCode(userId, roleCode);
        if (!roles.isEmpty()) userSession.setActiveUserRole(roles.get(0));
//
//        if (AppConstant.ROLE_CODE_VENDOR.equals(roleCode)) {
//            userSession.setVendor(vendorBeanRepository.getByUserId(userId));
//        }

        return userSession;
    }
}