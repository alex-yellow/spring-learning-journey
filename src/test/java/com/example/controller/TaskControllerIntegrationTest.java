package com.example.controller;

import com.example.springlearningjourney.controller.TaskController;
import com.example.springlearningjourney.dto.TaskDTO;
import com.example.springlearningjourney.mapper.TaskMapper;
import com.example.springlearningjourney.model.Task;
import com.example.springlearningjourney.SpringLearningJourneyApplication;
import com.example.springlearningjourney.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SpringLearningJourneyApplication.class) // укажите ваш главный класс Spring Boot
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private TaskMapper taskMapper;

    private final String BASE_URL = "/api/v1/tasks";

    @Test
    void getAllTasks_ShouldReturn200() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    void createTask_WithValidData_ShouldReturn201() throws Exception {
        String taskJson = """
            {
                "title": "Test Task",
                "description": "Test Description",
                "completed": false
            }
            """;

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");

        when(taskMapper.toEntity(any(TaskDTO.class))).thenReturn(task);
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Task"));
    }

    @Test
    void updateTask_WithValidData_ShouldReturn200() throws Exception {
        String taskJson = """
            {
                "title": "Updated Task",
                "description": "Updated Description",
                "completed": true
            }
            """;

        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setTitle("Updated Task");
        updatedTask.setCompleted(true);

        when(taskMapper.toEntity(any(TaskDTO.class))).thenReturn(updatedTask);
        when(taskService.updateTask(eq(1L), any(Task.class))).thenReturn(updatedTask);

        mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Updated Task"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void deleteTask_ShouldReturn204() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/1"))
                .andExpect(status().isNoContent());
    }
}