package com.softz.identity_service.service;

import com.softz.identity_service.dto.PermissionDto;
import com.softz.identity_service.dto.request.NewPermissionRequest;
import com.softz.identity_service.entity.Permission;
import com.softz.identity_service.exception.AppException;
import com.softz.identity_service.exception.ErrorCode;
import com.softz.identity_service.mapper.PermissionMapper;
import com.softz.identity_service.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionDto createPermission(NewPermissionRequest newPermissionRequest) {
        // Mapping to Permission entity
        Permission permission = permissionMapper.toPermission(newPermissionRequest);

        try {
            permission = permissionRepository.save(permission);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.PERMISSION_EXISTED);
        }

        // Mapping to PermissionDto
        return permissionMapper.toPermissionDto(permission);
    }

    public List<PermissionDto> getPermissions() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toPermissionDto)
                .toList();
    }

    public List<Permission> getPermissions(List<Integer> permissions) {
        return permissionRepository.findByIdIn(permissions);
    }
}
