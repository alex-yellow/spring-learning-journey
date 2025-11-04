package com.example.springlearningjourney.service;

import com.example.springlearningjourney.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    public List<Task> getAll(){
        return new ArrayList<>(tasks);
    }
    public Task crate(String title){
        Task task = new Task(nextId++, title, false);
        tasks.add(task);
        return task;
    }
}
