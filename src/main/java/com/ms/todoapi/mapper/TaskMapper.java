package com.ms.todoapi.mapper;

import com.ms.todoapi.dto.TaskRequest;
import com.ms.todoapi.dto.TaskResponse;
import com.ms.todoapi.model.entity.Task;
import com.ms.todoapi.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface TaskMapper {
    TaskResponse taskToTaskResponse(Task task);
    List<TaskResponse> tasksToTaskResponses(List<Task> tasks);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", source = "user")
    Task toEntity(TaskRequest request, User user);
}
