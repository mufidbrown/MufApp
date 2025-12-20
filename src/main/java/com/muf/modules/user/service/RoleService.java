package com.muf.modules.user.service;

import com.muf.modules.user.dto.RoleRequest;
import com.muf.modules.user.dto.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest roleRequest);
/*    RoleResponse getRole(Integer id);
    List<RoleResponse> getAllRoles();*/
}
