package com.ms.todoapi.mapper;

import com.ms.todoapi.dto.UserResponse;
import com.ms.todoapi.model.entity.Role;
import com.ms.todoapi.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToNames")
    UserResponse userToUserResponse(User user);

    List<UserResponse> usersToUserResponses(List<User> users);

    @Named("mapRolesToNames")
    static Set<String> mapRolesToNames(Set<Role> roles){
        if (roles == null) return Set.of();
        return roles.stream()
                .map(Role::getRole)
                .collect(Collectors.toSet());
    }
}
