package com.ms.todoapi.service;

import com.ms.todoapi.model.entity.Task;
import com.ms.todoapi.model.entity.User;
import com.ms.todoapi.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void deleteByIdAndUser(Long id, User user) {
        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id: " + id + " not found"));
        taskRepository.delete(task);
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }
}
