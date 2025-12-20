package com.muf.modules.user.service;

import com.muf.modules.user.dto.UserRoleRequest;
import com.muf.modules.user.dto.UserRoleResponse;

import java.util.List;

public interface UserRoleService {

    UserRoleResponse assignRole(UserRoleRequest userRoleRequest);
//
//    UserRoleResponse getById(Integer id);
//    List<UserRoleResponse> getByUser(Integer userId);
//    List<UserRoleResponse> getAll();
}
