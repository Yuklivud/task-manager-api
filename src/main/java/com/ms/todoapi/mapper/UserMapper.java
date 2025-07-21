package com.ms.todoapi.mapper;

import com.ms.todoapi.dto.UserResponse;
import com.ms.todoapi.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserResponse userToUserResponse(User user);
    List<UserResponse> usersToUserResponses(List<User> users);
}
