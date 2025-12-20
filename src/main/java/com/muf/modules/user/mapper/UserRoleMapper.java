package com.muf.modules.user.mapper;

import com.muf.modules.user.dto.UserRoleRequest;
import com.muf.modules.user.dto.UserRoleResponse;
import com.muf.modules.user.entity.domain.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserRole toEntity(UserRoleRequest userRoleRequest);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.code")
    @Mapping(target = "roleId", source = "role.id")
    @Mapping(target = "roleName", source = "role.name")
    UserRoleResponse toResponse(UserRole entity);
    
    List<UserRoleResponse> toResponseList(List<UserRole> entityList);


}
