package com.softz.identity_service.mapper;


import com.softz.identity_service.dto.UserDto;
import com.softz.identity_service.dto.request.NewUserRequest;
import com.softz.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "userId", source = "id")
    UserDto toUserDto(User user);
    User toUser(NewUserRequest newUserRequest);
}
