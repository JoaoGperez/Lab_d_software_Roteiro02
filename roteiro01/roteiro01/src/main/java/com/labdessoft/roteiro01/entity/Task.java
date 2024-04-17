package com.labdessoft.roteiro01.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Todos os detalhes sobre uma tarefa. ")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(name = "Descrição da tarefa deve possuir pelo menos 10 caracteres")
    @Size(min = 10, message = "Descrição da tarefa deve possuir pelo menos 10 caracteres")
    private String description;
    private Boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    // Método para marcar uma tarefa como concluída
    public void markAsCompleted() {
        this.completed = true;
    }

    // Método para adicionar uma nova tarefa
    public static Task addNewTask(String description) {
        return new Task(description);
    }

    // Método para excluir uma tarefa
    public static void deleteTask(Task task) {
        // Implemente aqui a lógica para excluir a tarefa do banco de dados
    }

    // Método para alterar uma tarefa
    public void updateTask(String newDescription) {
        this.description = newDescription;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", description=" + description + ", completed=" +
                completed + "]";
    }

    public boolean isCompleted() {
        return completed;
    }
}