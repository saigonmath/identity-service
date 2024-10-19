package com.softz.identity_service.controller;

import com.softz.identity_service.dto.ApiResponse;
import com.softz.identity_service.dto.UserDto;
import com.softz.identity_service.dto.request.NewUserRequest;
import com.softz.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ApiResponse<UserDto> createUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        var userDto = userService.createUser(newUserRequest);
        return ApiResponse.<UserDto>builder()
                .result(userDto)
                .build();
    }

    @GetMapping("/users")
    public ApiResponse<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getUsers();
        return ApiResponse.<List<UserDto>>builder()
                .result(users)
                .build();
    }

    @GetMapping("/users/{userId}")
    public UserDto getUserById(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/users/filter")
    public UserDto getUserByUsername(@Param("username") String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping("/users/filters")
    public UserDto getUserByUsernamea(@Param("username") String username){
        return userService.getUserByUsername(username);
    }
}
