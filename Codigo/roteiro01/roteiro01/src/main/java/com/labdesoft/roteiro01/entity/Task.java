package com.labdesoft.roteiro01.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Boolean completed;
    private TaskType type; // Novo campo para indicar o tipo de tarefa
    private TaskPriority priority;
    private LocalDate dueDate;
    private LocalDate createdDate;
    private Integer daysToComplete;
    private String status;


    // Constructor
    public Task(long id, String description, boolean completed) {
        this.id = id;
        this.description = description;
        this.completed = completed;
    }


    public enum TaskType {  // Enumeração para representar os tipos de tarefa
        DATA,   // Tarefa com data prevista de conclusão
        PRAZO,  // Tarefa com prazo previsto de conclusão em dias
        LIVRE   // Tarefa sem data ou prazo previsto de conclusão
    }

    public enum TaskPriority {  // Enumeração para representar a prioridade de tarefa
        ALTA,
        MEDIA,
        BAIXA
    }
}
