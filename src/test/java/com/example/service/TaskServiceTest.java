package com.example.service;

import com.example.springlearningjourney.model.Task;
import com.example.springlearningjourney.repository.TaskRepository;
import com.example.springlearningjourney.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void getAllTasks_shouldReturnAllTasks() {
        Task task1 = new Task(1L, "task1", false);
        Task task2 = new Task(2L, "task2", true);

        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

        List<Task> result = taskService.getAllTasks();

        assertEquals("task1", result.get(0).getTitle());
        assertEquals(2L, result.get(1).getId());
        assertEquals(true, result.get(1).isCompleted());

        verify(taskRepository).findAll();
    }

    @Test
    void createTask_shouldSaveAndReturnTask() {
        // given
        Task input = new Task(null, "New Task", false);
        Task saved = new Task(1L, "New Task", false);
        when(taskRepository.save(input)).thenReturn(saved);

        // when
        Task result = taskService.createTask(input);

        // then
        assertNotNull(result.getId());
        assertEquals("New Task", result.getTitle());
        verify(taskRepository).save(input);
    }

    @Test
    void updateTask_shouldUpdateExistingTask() {
        // given
        Long id = 1L;
        Task existing = new Task(id, "Old", false);
        Task update = new Task(null, "Updated", true);

        when(taskRepository.findById(id)).thenReturn(Optional.of(existing));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Task result = taskService.updateTask(id, update);

        // then
        assertEquals("Updated", result.getTitle());
        assertTrue(result.isCompleted());
        verify(taskRepository).findById(id);
        verify(taskRepository).save(existing);
    }

    @Test
    void updateTask_shouldThrowNotFound_whenTaskDoesNotExist() {
        // given
        Long id = 99L;
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        // when & then
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> taskService.updateTask(id, new Task())
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Task not found", exception.getReason());
        verify(taskRepository).findById(id);
        verify(taskRepository, never()).save(any());
    }

    @Test
    void deleteTask_shouldCallRepositoryDelete() {
        Long id = 1L;
        Task task = new Task(id, "Task", false);
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        taskService.deleteTask(id);

        verify(taskRepository).findById(id);
        verify(taskRepository).delete(task);
    }

    @Test
    void deleteTask_shouldThrowNotFound_whenTaskDoesNotExist() {
        Long id = 99L;
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> taskService.deleteTask(id)
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Task not found", exception.getReason());
        verify(taskRepository).findById(id);
        verify(taskRepository, never()).delete(any());
    }
}