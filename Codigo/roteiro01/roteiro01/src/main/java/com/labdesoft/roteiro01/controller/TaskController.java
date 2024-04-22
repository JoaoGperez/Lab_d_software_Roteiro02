package com.labdesoft.roteiro01.controller;

import com.labdesoft.roteiro01.entity.Task;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {


    @GetMapping("/task")
    @Operation(summary = "Lista todas as tarefas da lista")
    public List<Task> listAll() {
        return tasks;
    }
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;
    @PostMapping("/add")
    public String addTask(@RequestBody Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return "Tarefa Adicionada";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                return "Tarefa Removida";
            }
        }
        return "ID inválido para exclusão";
    }

    @PutMapping("/update/{id}")
    public String updateTask(@PathVariable int id, @RequestBody Task updateTask) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(updateTask.getDescription());
                return "Tarefa Alterada";
            }
        }
        return "ID inválido para alteração";
    }

    @PutMapping("/completed/{id}")
    public String completedTask(@PathVariable int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setCompleted(true);
                return "Tarefa Concluída";
            }
        }
        return "ID inválido para conclusão de tarefa";
    }
}
