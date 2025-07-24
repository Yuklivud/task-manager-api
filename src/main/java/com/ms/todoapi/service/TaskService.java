package com.ms.todoapi.service;

import com.ms.todoapi.model.entity.Task;
import com.ms.todoapi.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task with id: " + id + " not found"));
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
