package com.muf.modules.user.service;

import com.muf.modules.user.dto.RoleRequest;
import com.muf.modules.user.dto.RoleResponse;
import com.muf.modules.user.entity.domain.Role;
import com.muf.modules.user.mapper.RoleMapper;
import com.muf.modules.user.mapper.UserMapper;
import com.muf.modules.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {
        Role role = roleMapper.toEntity(roleRequest);
        roleRepository.save(role);
        return roleMapper.toResponse(role);
    }
}
