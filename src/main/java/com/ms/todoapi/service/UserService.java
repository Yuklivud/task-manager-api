package com.ms.todoapi.service;

import com.ms.todoapi.dto.UserDto;
import com.ms.todoapi.model.entity.Role;
import com.ms.todoapi.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {

    public UserDto mapToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getIsActive(),
                user.getCreatedAt(),
                user.getRoles()
                        .stream()
                        .map(Role::getRole)
                        .collect(Collectors.toSet())
        );
    }
}
