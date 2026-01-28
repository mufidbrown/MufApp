package com.muf.modules.user.service;

import com.muf.modules.user.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    UserResponse updateUser(Integer userId, UpdateUserRequest request);
    void deleteUser(Integer userId);
    UserResponse getUserById(Integer userId);
    Page<UserResponse> getAllUsers(Pageable pageable);
    UserResponse updatePersonalInfo(String email, UpdatePersonalInfoRequest request);
    UserResponse getCurrentUser(String email);
}
