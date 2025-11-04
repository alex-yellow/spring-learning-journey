package com.example.springlearningjourney.controller;

import com.example.springlearningjourney.model.Task;
import com.example.springlearningjourney.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task task){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(id, task));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
