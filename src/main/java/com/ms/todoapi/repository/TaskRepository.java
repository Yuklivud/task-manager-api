package com.ms.todoapi.repository;

import com.ms.todoapi.model.entity.Task;
import com.ms.todoapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByIdAndUser(Long id, User user);
}
