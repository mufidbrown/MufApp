package com.muf.modules.user.mapper;

import com.muf.modules.user.dto.RoleRequest;
import com.muf.modules.user.dto.RoleResponse;
import com.muf.modules.user.entity.domain.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleRequest roleRequest);

    RoleResponse toResponse(Role role);
    List<RoleResponse> toResponseList(List<Role> entityList);
}
