package com.muf.modules.master.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {}

//
//    private final UserRoleRepository userRoleRepository;
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//
//    // ===============================
//    // ASSIGN ROLE (REGISTER / ADMIN)
//    // ===============================
//    @Override
//    public UserRoleResponse assignRole(UserRoleRequest request) {
//
//        User user = userRepository.findById(request.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Role role = roleRepository.findById(request.getRoleId())
//                .orElseThrow(() -> new RuntimeException("Role not found"));
//
//        // ❗ Prevent duplicate role
//        if (userRoleRepository.existsByUserIdAndRoleId(user.getId(), role.getId())) {
//            throw new RuntimeException("User already has this role");
//        }
//
//        UserRole userRole = new UserRole();
//        userRole.setUser(user);
//        userRole.setRole(role);
//        userRole.setCreatedAt(new Date());
//
//        userRoleRepository.save(userRole);
//
//        return UserRoleResponse.builder()
//                .userId(user.getId())
//                .roleId(role.getId())
//                .roleCode(role.getCode())
//                .build();
//