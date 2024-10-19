package com.softz.identity_service.service;

import com.softz.identity_service.dto.RoleDto;
import com.softz.identity_service.dto.request.NewRoleRequest;
import com.softz.identity_service.entity.Permission;
import com.softz.identity_service.entity.Role;
import com.softz.identity_service.exception.AppException;
import com.softz.identity_service.exception.ErrorCode;
import com.softz.identity_service.mapper.RoleMapper;
import com.softz.identity_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {

    RoleRepository roleRepository;
    PermissionService permissionService;
    RoleMapper roleMapper;

    public RoleDto createRole(NewRoleRequest request) {
        List<Integer> idList = request.getPermissions();
        List<Permission> permissions = permissionService.getPermissions(idList);

        // Validate
        if (!CollectionUtils.isEmpty(request.getPermissions())
                && idList.size() != permissions.size()) {
            throw new AppException(ErrorCode.INVALID_INPUT);
        }

        Role role = roleMapper.toRole(request);
        role.setPermissions(Set.copyOf(permissions));

        return roleMapper.toRoleDto(roleRepository.save(role));
    }
}
