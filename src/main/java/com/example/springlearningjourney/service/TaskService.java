package com.example.springlearningjourney.service;

import com.example.springlearningjourney.model.Task;
import com.example.springlearningjourney.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    public Task createTask(Task task){
        return taskRepository.save(task);
    }
    public Task updateTask(Long id, Task task){
        Task updatedTask = taskRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        updatedTask.setTitle(task.getTitle());
        updatedTask.setCompleted(task.isCompleted());
        return taskRepository.save(updatedTask);
    }
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        taskRepository.delete(task);
    }
}
