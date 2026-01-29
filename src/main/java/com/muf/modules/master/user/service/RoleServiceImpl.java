package com.muf.modules.master.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
