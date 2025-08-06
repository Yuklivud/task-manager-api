package com.ms.todoapi.controller;

import com.ms.todoapi.dto.TaskRequest;
import com.ms.todoapi.dto.TaskResponse;
import com.ms.todoapi.mapper.TaskMapper;
import com.ms.todoapi.model.entity.Task;
import com.ms.todoapi.model.entity.User;
import com.ms.todoapi.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/tasks")
@Tag(name = "Task Controller", description = "Task management operations")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @Operation(summary = "Get task by ID")
    @ApiResponse(responseCode = "200", description = "Task found")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        TaskResponse dto = taskMapper.taskToTaskResponse(task);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Get all tasks")
    @ApiResponse(responseCode = "200", description = "List of all tasks")
    @GetMapping()
    public ResponseEntity<List<TaskResponse>> findAll() {
        List<TaskResponse> dto = taskMapper.tasksToTaskResponses(taskService.findAll());
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Delete task by ID")
    @ApiResponse(responseCode = "204", description = "Task deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@RequestParam Long id,
                                                   @AuthenticationPrincipal User user) {
        taskService.deleteByIdAndUser(id, user);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Create a new task")
    @ApiResponse(responseCode = "201", description = "Task created")
    @PostMapping()
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest,
                                                   @AuthenticationPrincipal User user) {
        Task task = taskMapper.toEntity(taskRequest, user);
        taskService.create(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

