package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto {
    private long ID;
    private String name;

    public static PermissionDto toPermissionDto(Permission permission) {
        return new PermissionDto(permission.getID(), permission.getName());
    }
}
