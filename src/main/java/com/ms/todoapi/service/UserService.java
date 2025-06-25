package com.ms.todoapi.service;

import com.ms.todoapi.dto.UserDto;
import com.ms.todoapi.model.entity.Role;
import com.ms.todoapi.model.entity.User;
import com.ms.todoapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }
}
