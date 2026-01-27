package com.muf.modules.user.service;

import com.muf.modules.user.ChangePasswordRequest;
import com.muf.modules.user.CreateUserRequest;
import com.muf.modules.user.UpdatePersonalInfoRequest;
import com.muf.modules.user.UpdateUserRequest;
import com.muf.modules.user.dto.UserResponse;
import com.muf.modules.user.entity.domain.Role;
import com.muf.modules.user.entity.domain.User;
import com.muf.modules.user.entity.domain.UserRole;

import com.muf.modules.user.repository.RoleRepository;
import com.muf.modules.user.repository.UserRepository;
import com.muf.modules.user.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
//        user.setIsActive(request.getIsActive());
//        user.setIsLocked(false);
        user.setLoginAttempt(0);
        user.setUsed(false);
        user.setCreatedAt(new Date());
//        user.setIsDeleted(BaseEntity.ENTITY_FLAG_NOT_DELETED);

        userRepository.save(user);

        for (Integer roleId : request.getRoleIds()) {
            Role role = roleRepository.findById(roleId.intValue())
                    .orElseThrow(() -> new RuntimeException("Role with ID " + roleId + " not found"));

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
//            userRole.setIsDefault(false);
            userRole.setStartDate(new Date());
            userRole.setCreatedAt(new Date());
//            userRole.setIsDeleted(BaseEntity.ENTITY_FLAG_NOT_DELETED);
            userRoleRepository.save(userRole);
        }

        return toUserResponse(user);
    }

    private UserResponse toUserResponse(User user) {
        return null;
    }


}
