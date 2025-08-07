package com.ms.todoapi.controller;

import com.ms.todoapi.config.SecurityConfig;
import com.ms.todoapi.dto.UserResponse;
import com.ms.todoapi.mapper.UserMapper;
import com.ms.todoapi.model.entity.Role;
import com.ms.todoapi.model.entity.User;
import com.ms.todoapi.security.JwtFilter;
import com.ms.todoapi.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtFilter.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        })
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Test
    void findById_ReturnsUserResponse_WhenUserExists() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setEmail("usertofind@gmail.com");
        user.setActive(true);
        user.setCreatedAt(LocalDate.now());
        user.setRoles(Set.of(new Role(1, "ROLE_USER")));

        UserResponse userResponse = new UserResponse(1L, "usertofind@gmail.com", true, LocalDate.now(), Set.of("ROLE_USER"));

        when(userService.findById(1L)).thenReturn(user);
        when(userMapper.userToUserResponse(user)).thenReturn(userResponse);

        mockMvc.perform(get("/api/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("usertofind@gmail.com"));
    }

    @Test
    void findById_Returns404_WhenUserDoesNotExist() throws Exception {
        Long userId = 999L;
        when(userService.findById(userId)).thenThrow(new EntityNotFoundException("User not found"));

        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_ReturnsListOfUsers() throws Exception {
        User user1 = new User(); user1.setId(1L); user1.setEmail("usertofind1@gmail.com"); user1.setActive(true); user1.setCreatedAt(LocalDate.now()); user1.setRoles(Set.of(new Role(1, "ROLE_USER")));
        User user2 = new User(); user2.setId(2L); user2.setEmail("usertofind2@gmail.com"); user2.setActive(true); user2.setCreatedAt(LocalDate.now()); user2.setRoles(Set.of(new Role(1, "ROLE_USER")));

        UserResponse userResponse1 = new UserResponse(1L, "usertofind1@gmail.com", true, LocalDate.now(), Set.of("ROLE_USER"));
        UserResponse userResponse2 = new UserResponse(2L, "usertofind2@gmail.com", true, LocalDate.now(), Set.of("ROLE_USER"));

        when(userService.findAll()).thenReturn(List.of(user1, user2));
        when(userMapper.usersToUserResponses(List.of(user1, user2))).thenReturn(List.of(userResponse1, userResponse2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].email").value("usertofind1@gmail.com"))
                .andExpect(jsonPath("$[1].email").value("usertofind2@gmail.com"));

    }

    @Test
    void findAll_ReturnsEmptyList_WhenUserDoesNotExist() throws Exception {
        when(userService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
