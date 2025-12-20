package com.muf.modules.user.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class RoleResponse {
    private Integer id;
    private String name;
    private Integer type;
    private Date createdAt;
    private LocalDateTime updatedAt;
}
