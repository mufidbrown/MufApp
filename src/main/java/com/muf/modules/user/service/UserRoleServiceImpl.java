package com.muf.modules.user.service;

import com.muf.modules.user.dto.RoleRequest;
import com.muf.modules.user.dto.UserRoleRequest;
import com.muf.modules.user.dto.UserRoleResponse;
import com.muf.modules.user.entity.domain.Role;
import com.muf.modules.user.entity.domain.User;
import com.muf.modules.user.entity.domain.UserRole;
import com.muf.modules.user.mapper.UserRoleMapper;
import com.muf.modules.user.repository.RoleRepository;
import com.muf.modules.user.repository.UserRepository;
import com.muf.modules.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleMapper userRoleMapper;

    @Override
    public UserRoleResponse assignRole(UserRoleRequest userRoleRequest) {

        //validate user
        User user = userRepository.findById(userRoleRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        //validate role
        Role role = roleRepository.findById(userRoleRequest.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role Not Found"));


        // convert request -> entity
        UserRole userRole = userRoleMapper.toEntity(userRoleRequest);
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
        return userRoleMapper.toResponse(userRole);
    }
}
