package com.muf.modules.user.service;

import com.muf.modules.user.dto.UserRequest;
import com.muf.modules.user.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
//    List<UserResponse> getAllUsers();
}
