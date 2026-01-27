package com.muf.modules.user.service;

import com.muf.modules.authentication.register.RegisterRequest;
import com.muf.modules.user.ChangePasswordRequest;
import com.muf.modules.user.CreateUserRequest;
import com.muf.modules.user.UpdatePersonalInfoRequest;
import com.muf.modules.user.UpdateUserRequest;
import com.muf.modules.user.dto.UserRequest;
import com.muf.modules.user.dto.UserResponse;
import com.muf.modules.user.entity.domain.Role;
import com.muf.modules.user.entity.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
//    UserResponse updateUser(Long userId, UpdateUserRequest request);
//    void deleteUser(Long userId);
//    UserResponse getUserById(Long userId);
//    Page<UserResponse> getAllUsers(Pageable pageable);
//    UserResponse updatePersonalInfo(String email, UpdatePersonalInfoRequest request);
//    void changePassword(String email, ChangePasswordRequest request);
//    UserResponse getCurrentUser(String email);
}
