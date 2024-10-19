package com.softz.identity_service.mapper;

import com.softz.identity_service.dto.PermissionDto;
import com.softz.identity_service.dto.request.NewPermissionRequest;
import com.softz.identity_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDto toPermissionDto(Permission permission);
    Permission toPermission(NewPermissionRequest newPermissionRequest);
}
