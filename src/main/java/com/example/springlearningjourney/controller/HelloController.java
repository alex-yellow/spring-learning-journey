package com.example.springlearningjourney.controller;

import com.example.springlearningjourney.dto.TaskDTO;
import com.example.springlearningjourney.model.Task;
import com.example.springlearningjourney.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class HelloController {
    private TaskService taskService;
    public HelloController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring!";
    }
    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name){
        return "Hello " + name;
    }

    @PostMapping("/hello")
    public String helloMyName(@RequestBody Map<String, String> body){
        String message = body.getOrDefault("message", "not speak");
        return "You speak: \"" + message + "\"";
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAll());
    }
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskDTO taskDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.crate(taskDTO));
    }
}
