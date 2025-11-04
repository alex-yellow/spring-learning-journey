package com.example.springlearningjourney.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class HelloController {
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
}
