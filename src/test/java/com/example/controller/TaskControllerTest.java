package com.example.controller;


import com.example.springlearningjourney.controller.TaskController;
import com.example.springlearningjourney.dto.TaskDTO;
import com.example.springlearningjourney.mapper.TaskMapper;
import com.example.springlearningjourney.model.Task;
import com.example.springlearningjourney.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskController taskController;

    private final Long TASK_ID = 1L;
    private final TaskDTO taskDTO = createTaskDTO();
    private final Task task = createTask();

    @Test
    void getAllTasks_ShouldReturnListOfTasks() {
        // Arrange
        List<Task> expectedTasks = Arrays.asList(task, createTask(2L, "Test Task 2"));
        when(taskService.getAllTasks()).thenReturn(expectedTasks);

        // Act
        ResponseEntity<List<Task>> response = taskController.getAllTasks();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(expectedTasks, response.getBody());
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void getAllTasks_WhenNoTasks_ShouldReturnEmptyList() {
        // Arrange
        when(taskService.getAllTasks()).thenReturn(List.of());

        // Act
        ResponseEntity<List<Task>> response = taskController.getAllTasks();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void createTask_WithValidData_ShouldReturnCreatedTask() {
        // Arrange
        when(taskMapper.toEntity(taskDTO)).thenReturn(task);
        when(taskService.createTask(task)).thenReturn(task);

        // Act
        ResponseEntity<Task> response = taskController.createTask(taskDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(task, response.getBody());
        verify(taskMapper, times(1)).toEntity(taskDTO);
        verify(taskService, times(1)).createTask(task);
    }

    @Test
    void createTask_ShouldCallMapperWithCorrectDTO() {
        // Arrange
        when(taskMapper.toEntity(taskDTO)).thenReturn(task);
        when(taskService.createTask(task)).thenReturn(task);

        // Act
        taskController.createTask(taskDTO);

        // Assert
        verify(taskMapper, times(1)).toEntity(taskDTO);
    }

    @Test
    void updateTask_WithValidData_ShouldReturnUpdatedTask() {
        // Arrange
        Task updatedTask = createTask();
        updatedTask.setTitle("Updated Title");

        when(taskMapper.toEntity(taskDTO)).thenReturn(task);
        when(taskService.updateTask(eq(TASK_ID), any(Task.class))).thenReturn(updatedTask);

        // Act
        ResponseEntity<Task> response = taskController.updateTask(TASK_ID, taskDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Title", response.getBody().getTitle());
        verify(taskMapper, times(1)).toEntity(taskDTO);
        verify(taskService, times(1)).updateTask(eq(TASK_ID), any(Task.class));
    }

    @Test
    void updateTask_ShouldCallServiceWithCorrectId() {
        // Arrange
        when(taskMapper.toEntity(taskDTO)).thenReturn(task);
        when(taskService.updateTask(eq(TASK_ID), any(Task.class))).thenReturn(task);

        // Act
        taskController.updateTask(TASK_ID, taskDTO);

        // Assert
        verify(taskService, times(1)).updateTask(eq(TASK_ID), any(Task.class));
    }

    @Test
    void deleteTask_WithValidId_ShouldReturnNoContent() {
        // Arrange
        doNothing().when(taskService).deleteTask(TASK_ID);

        // Act
        ResponseEntity<Void> response = taskController.deleteTask(TASK_ID);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(taskService, times(1)).deleteTask(TASK_ID);
    }

    @Test
    void deleteTask_ShouldCallServiceWithCorrectId() {
        // Arrange
        doNothing().when(taskService).deleteTask(TASK_ID);

        // Act
        taskController.deleteTask(TASK_ID);

        // Assert
        verify(taskService, times(1)).deleteTask(TASK_ID);
    }

    // Helper methods to create test data
    private TaskDTO createTaskDTO() {
        TaskDTO dto = new TaskDTO();
        dto.setTitle("Test Task");
        dto.setCompleted(false);
        return dto;
    }

    private Task createTask() {
        return createTask(TASK_ID, "Test Task");
    }

    private Task createTask(Long id, String title) {
        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setCompleted(false);
        return task;
    }
}