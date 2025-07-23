package com.ms.todoapi.controller;

import com.ms.todoapi.dto.UserResponse;
import com.ms.todoapi.mapper.UserMapper;
import com.ms.todoapi.model.entity.User;
import com.ms.todoapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@Tag(name = "User Controller", description = "User-related operation")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findByID(@PathVariable Long id) {
        User user = userService.findById(id);
        UserResponse dto = userMapper.userToUserResponse(user);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user info")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal User user) {
        UserResponse dto = userMapper.userToUserResponse(user);
        return ResponseEntity.ok(dto);
    }
}