package com.softz.identity_service.service;

import com.softz.identity_service.dto.UserDto;
import com.softz.identity_service.dto.request.NewUserRequest;
import com.softz.identity_service.entity.User;
import com.softz.identity_service.exception.AppException;
import com.softz.identity_service.exception.ErrorCode;
import com.softz.identity_service.mapper.UserMapper;
import com.softz.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public UserDto createUser(NewUserRequest newUserRequest) {
        // Mapping to User entity
        User user = userMapper.toUser(newUserRequest);

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        // Mapping to UserDto
        return userMapper.toUserDto(user);
    }
/*
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new RuntimeException("User not found"));
    }
*/
    public UserDto getUserById(String userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND, userId));
        // Mapping to UserDto
        UserDto userDto = new UserDto();
        if (user != null){
            userDto.setUserId(user.getId());
            userDto.setUsername(user.getUsername());
        } else {
            userDto.setUserId("not found");
            userDto.setUsername("not found");
        }

        return userDto;
    }

    public UserDto getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        // Mapping to UserDto
        UserDto userDto = new UserDto();
        if (user != null){
            userDto.setUserId(user.getId());
            userDto.setUsername(user.getUsername());
        } else {
            userDto.setUserId("not found");
            userDto.setUsername("not found");
        }

        return userDto;
    }

    public List<UserDto> getUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .toList();
    }
}

