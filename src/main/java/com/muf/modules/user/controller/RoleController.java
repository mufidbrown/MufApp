package com.muf.modules.user.controller;


import com.muf.modules.user.dto.RoleRequest;
import com.muf.modules.user.dto.RoleResponse;
import com.muf.modules.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
//@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    public RoleResponse create (@RequestBody RoleRequest roleRequest) {
        return roleService.createRole(roleRequest);
    }

}
