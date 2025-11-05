package com.example.springlearningjourney.mapper;

import com.example.springlearningjourney.dto.TaskDTO;
import com.example.springlearningjourney.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskDTO taskDTO);
    TaskDTO toDTO(Task task);
}