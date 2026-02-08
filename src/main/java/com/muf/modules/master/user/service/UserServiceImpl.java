package com.muf.modules.master.user.service;

import com.muf.modules.master.user.dto.CreateUserRequest;
import com.muf.modules.master.user.dto.UpdatePersonalInfoRequest;
import com.muf.modules.master.user.dto.UpdateUserRequest;
import com.muf.modules.master.user.dto.UserResponse;
import com.muf.modules.master.user.entity.domain.Role;
import com.muf.modules.master.user.entity.domain.User;
import com.muf.modules.master.user.entity.domain.UserRole;

import com.muf.modules.master.user.repository.RoleRepository;
import com.muf.modules.master.user.repository.UserRepository;
import com.muf.modules.master.user.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setActive(true);
        user.setLocked(false);
        user.setLoginAttempt(0);
        user.setUsed(false);
        user.setCreatedAt(new Date());

        userRepository.save(user);

        // Assign roles
        assignRolesToUser(user, request.getRoleIds());

        return toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Integer userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update basic info
        user.setFullName(request.getFullName());
        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
            user.setEmail(request.getEmail());
        }

        if (request.getIsActive() != null) user.setActive(request.getIsActive());
        if (request.getIsLocked() != null) {
            user.setLocked(request.getIsLocked());
            if (!request.getIsLocked()) {
                user.setLoginAttempt(0);
                user.setLoginAttemptDate(null);
            }
        }

        user.setUpdatedAt(new Date());
        userRepository.save(user);

        // Update roles
        if (request.getRoleIds() != null && !request.getRoleIds().isEmpty()) {
            // Remove old roles
            List<UserRole> oldRoles = userRoleRepository.findByUser(user);
            userRoleRepository.deleteAll(oldRoles);

            // Assign new roles
            assignRolesToUser(user, request.getRoleIds());
        }

        return toUserResponse(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Hard delete user roles first
        List<UserRole> userRoles = userRoleRepository.findByUser(user);
        userRoleRepository.deleteAll(userRoles);

        // Hard delete user
        userRepository.delete(user);
    }

    @Override
    public UserResponse getUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toUserResponse(user);
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(this::toUserResponse);
    }

    @Override
    @Transactional
    public UserResponse updatePersonalInfo(String email, UpdatePersonalInfoRequest request) {
        User user = userRepository.findByEmailWithRoles(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(request.getFullName());
        user.setUpdatedAt(new Date());
        userRepository.save(user);

        return toUserResponse(user);
    }

    @Override
    public UserResponse getCurrentUser(String email) {
        User user = userRepository.findByEmailWithRoles(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toUserResponse(user);
    }

    // Utility method: assign roles to user
    private void assignRolesToUser(User user, List<Integer> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) return;

        for (Integer roleId : roleIds) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role with ID " + roleId + " not found"));
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRole.setStartDate(new Date());
            userRole.setCreatedAt(new Date());
            userRoleRepository.save(userRole);
        }
    }

    // Mapper method
    private UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setIsActive(user.getActive());
        response.setIsLocked(user.getLocked());
        return response;
    }
}
