package com.ms.todoapi.controller;

import com.ms.todoapi.dto.TaskResponse;
import com.ms.todoapi.mapper.TaskMapper;
import com.ms.todoapi.model.entity.Task;
import com.ms.todoapi.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        TaskResponse dto = taskMapper.taskToTaskResponse(task);
        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    public ResponseEntity<List<TaskResponse>> findAll() {
        List<TaskResponse> dto = taskMapper.tasksToTaskResponses(taskService.findAll());
        return ResponseEntity.ok(dto);
    }
}
