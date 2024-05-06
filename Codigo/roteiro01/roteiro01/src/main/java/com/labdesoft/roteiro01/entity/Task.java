package com.labdesoft.roteiro01.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    // Construtores, Getters e Setters...

    // Enumeração para representar os tipos de tarefa
    public enum TaskType {
        DATA,   // Tarefa com data prevista de conclusão
        PRAZO,  // Tarefa com prazo previsto de conclusão em dias
        LIVRE   // Tarefa sem data ou prazo previsto de conclusão
    }
}
