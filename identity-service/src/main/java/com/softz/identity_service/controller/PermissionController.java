package com.softz.identity_service.controller;

import com.softz.identity_service.dto.ApiResponse;
import com.softz.identity_service.dto.PermissionDto;
import com.softz.identity_service.dto.request.NewPermissionRequest;
import com.softz.identity_service.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/permissions")
    public ApiResponse<PermissionDto> createPermission(@RequestBody @Valid NewPermissionRequest newPermissionRequest) {
        var permissionDto = permissionService.createPermission(newPermissionRequest);
        return ApiResponse.<PermissionDto>builder()
                .result(permissionDto)
                .build();
    }

    @GetMapping("/permissions")
    public ApiResponse<List<PermissionDto>> getPermissions() {
        List<PermissionDto> permissions = permissionService.getPermissions();
        return ApiResponse.<List<PermissionDto>>builder()
                .result(permissions)
                .build();
    }
}
