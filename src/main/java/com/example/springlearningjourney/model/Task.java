package com.example.springlearningjourney.model;

public class Task {
    public Long id;
    public String title;
    public boolean completed;

    public Task(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}
