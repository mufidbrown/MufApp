package com.muf.modules.user.service;

import com.muf.modules.user.dto.RoleRequest;
import com.muf.modules.user.dto.RoleResponse;
import com.muf.modules.user.entity.domain.Role;
import com.muf.modules.user.entity.domain.User;
import com.muf.modules.user.entity.domain.UserRole;

import com.muf.modules.user.repository.RoleRepository;
import com.muf.modules.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private UserRoleRepository userRoleRepository;
//
//    @Override
//    public Role findById(Integer id) {
//        return roleRepository.findById(id).orElse(null);
//    }
//
//
//
//    @Override
//    public UserRole assignRoleToUser(User user, Role role) {
//        UserRole userRole = new UserRole();
//        userRole.setUser(user);
//        userRole.setRole(role);
//        userRole.setStartDate(new Date());
//
//        return userRoleRepository.save(userRole);
//    }
}
