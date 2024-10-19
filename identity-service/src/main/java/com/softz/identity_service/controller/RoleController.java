package com.softz.identity_service.controller;
import com.softz.identity_service.dto.ApiResponse;
import com.softz.identity_service.dto.RoleDto;
import com.softz.identity_service.dto.request.NewRoleRequest;
import com.softz.identity_service.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/roles")
    public ApiResponse<RoleDto> createRole(@RequestBody NewRoleRequest newRoleRequest) {
        var roleDto = roleService.createRole(newRoleRequest);
        return ApiResponse.<RoleDto>builder()
                .result(roleDto)
                .build();
    }
}
