package com.ms.todoapi.controller;

import com.ms.todoapi.dto.UserResponse;
import com.ms.todoapi.mapper.UserMapper;
import com.ms.todoapi.model.entity.User;
import com.ms.todoapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "User-related operations")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(summary = "Get user by ID")
    @ApiResponse(responseCode = "200", description = "User found")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findByID(@PathVariable Long id) {
        User user = userService.findById(id);
        UserResponse dto = userMapper.userToUserResponse(user);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Get current authenticated user")
    @ApiResponse(responseCode = "200", description = "Current user info")
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal User user) {
        UserResponse dto = userMapper.userToUserResponse(user);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "List of users")
    @GetMapping()
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> dto = userMapper.usersToUserResponses(userService.findAll());
        return ResponseEntity.ok(dto);
    }
}
