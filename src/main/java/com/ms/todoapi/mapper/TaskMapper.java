package com.ms.todoapi.mapper;

import com.ms.todoapi.dto.TaskResponse;
import com.ms.todoapi.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface TaskMapper {
    TaskResponse taskToTaskResponse(Task task);
    List<TaskResponse> tasksToTaskResponses(List<Task> tasks);
}
