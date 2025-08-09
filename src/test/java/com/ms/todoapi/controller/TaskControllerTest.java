package com.ms.todoapi.controller;

import com.ms.todoapi.config.SecurityConfig;
import com.ms.todoapi.dto.TaskResponse;
import com.ms.todoapi.dto.UserResponse;
import com.ms.todoapi.mapper.TaskMapper;
import com.ms.todoapi.mapper.UserMapper;
import com.ms.todoapi.model.entity.Role;
import com.ms.todoapi.model.entity.Task;
import com.ms.todoapi.model.entity.User;
import com.ms.todoapi.security.JwtFilter;
import com.ms.todoapi.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TaskController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtFilter.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
@AutoConfigureMockMvc(addFilters = false)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private TaskService taskService;

    private User user = new User();
    private Task task = new Task();
    private LocalDateTime now = LocalDateTime.of(2025, 10, 10, 10, 10);

    @BeforeEach
    void init(){
        user.setId(1L);
        user.setEmail("usertofind@gmail.com");
        user.setActive(true);
        user.setCreatedAt(LocalDate.from(now));
        user.setRoles(Set.of(new Role(1, "ROLE_USER")));

        task.setId(1L);
        task.setTitle("Task Title");
        task.setDescription("Task Description");
        task.setDeadline(now);
        task.setCreatedAt(now);
        task.setUpdatedAt(now);
        task.setUser(user);
    }

    @Test
    void findById_ReturnsTaskResponse_WhenTaskExists() throws Exception {
        UserResponse userResponse = new UserResponse(1L, "usertofind@gmail.com", true, LocalDate.from(now), Set.of("ROLE_USER"));
        TaskResponse taskResponse = new TaskResponse(1L, "Task Title", "Task Description", now, now, now, userResponse);

        when(userMapper.userToUserResponse(user)).thenReturn(userResponse);
        when(taskMapper.taskToTaskResponse(task)).thenReturn(taskResponse);
        when(taskService.findById(task.getId())).thenReturn(task);

        mockMvc.perform(get("/api/tasks/{id}", task.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(task.getId()))
                .andExpect(jsonPath("$.title").value(task.getTitle()));
    }

    @Test
    void findById_Returns404_WhenUserDoesNotExist() throws Exception {
        when(taskService.findById(999L)).thenThrow(new EntityNotFoundException("Task not found"));
        mockMvc.perform(get("/api/tasks/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_ReturnsListOfTaskResponse_WhenTaskExists() throws Exception {
        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task Title 2");
        task2.setDescription("Task Description 2");
        task2.setDeadline(now);
        task2.setCreatedAt(now);
        task2.setUpdatedAt(now);
        task2.setUser(user);

        UserResponse userResponse = new UserResponse(1L, "usertofind@gmail.com", true, LocalDate.from(now), Set.of("ROLE_USER"));
        TaskResponse taskResponse1 = new TaskResponse(1L, "Task Title", "Task Description", now, now, now, userResponse);
        TaskResponse taskResponse2 = new TaskResponse(2L, "Task Title 2", "Task Description 2", now, now, now, userResponse);

        when(taskService.findAll()).thenReturn(List.of(task, task2));
        when(taskMapper.tasksToTaskResponses(List.of(task, task2))).thenReturn(List.of(taskResponse1, taskResponse2));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(taskResponse1.id()))
                .andExpect(jsonPath("$[1].id").value(taskResponse2.id()));
    }

    @Test
    void findAll_ReturnsEmptyList_WhenTaskDoesNotExists() throws Exception {
        when(taskService.findAll()).thenReturn(List.of());
        when(taskMapper.tasksToTaskResponses(List.of())).thenReturn(List.of());

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void deleteById_ReturnsTaskResponse_WhenTaskExists() throws Exception {
        doNothing().when(taskService).deleteByIdAndUser(eq(task.getId()), eq(user));

        mockMvc.perform(delete("/api/tasks/{id}", task.getId())
                        .requestAttr("user", user))
                .andExpect(status().isNoContent());
    }
}
