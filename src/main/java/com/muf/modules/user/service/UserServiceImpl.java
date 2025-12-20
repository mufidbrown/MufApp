package com.muf.modules.user.service;

import com.muf.modules.user.dto.UserRequest;
import com.muf.modules.user.dto.UserResponse;
import com.muf.modules.user.entity.domain.User;
import com.muf.modules.user.mapper.RoleMapper;
import com.muf.modules.user.mapper.UserMapper;
import com.muf.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        userRepository.save(user);
        return userMapper.toResponse(user);
    }
}
