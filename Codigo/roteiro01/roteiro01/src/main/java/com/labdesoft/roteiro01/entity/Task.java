package com.labdesoft.roteiro01.entity;

public class Task {
    private long id;
    private String description;
    private boolean completed;

    // Construtor
    public Task(long id, String description, boolean completed) {
        this.id = id;
        this.description = description;
        this.completed = completed;
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Outros métodos, se necessário
}
