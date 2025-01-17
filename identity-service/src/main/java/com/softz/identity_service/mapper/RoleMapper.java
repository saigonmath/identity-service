package com.softz.identity_service.mapper;

import com.softz.identity_service.dto.RoleDto;
import com.softz.identity_service.dto.request.NewRoleRequest;
import com.softz.identity_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toRoleDto(Role role);
    @Mapping(target = "permissions", ignore = true)
    Role toRole(NewRoleRequest request);
}