package com.example.springlearningjourney.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskDTO {
    @NotBlank(message = "The field title is requires")
    private String title;

    public TaskDTO(String title) {
        this.title = title;
    }

    public @NotBlank(message = "The field title is requires") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "The field title is requires") String title) {
        this.title = title;
    }
}
