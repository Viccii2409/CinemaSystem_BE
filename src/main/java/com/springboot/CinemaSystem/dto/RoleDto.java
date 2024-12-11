package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private long ID;
    private String name;
    private long quantityUser;
    private List<PermissionDto> permissions;

    public RoleDto(long ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public static RoleDto toRoleDto(Role role) {
        return new RoleDto(
                role.getID(),
                role.getName(),
                role.getUsers().size(),
                role.getPermission().stream()
                        .map(entry -> PermissionDto.toPermissionDto(entry))
                        .collect(Collectors.toList())
        );
    }

    public static RoleDto toRoleDto2(Role role) {
        return new RoleDto(role.getID(), role.getName());
    }

}
