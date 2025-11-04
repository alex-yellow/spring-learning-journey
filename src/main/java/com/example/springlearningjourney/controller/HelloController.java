package com.example.springlearningjourney.controller;

import com.example.springlearningjourney.model.Task;
import com.example.springlearningjourney.service.TaskService;
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
    public List<Task> getAll(){
        return taskService.getAll();
    }
    @PostMapping("/tasks")
    public Task create(@RequestBody Map<String, String> body){
        String title = body.get("title");
        if(title == null || title.isBlank()){
            throw  new IllegalArgumentException("Title do not must be empty");
        }
        return taskService.crate(title);
    }
}
