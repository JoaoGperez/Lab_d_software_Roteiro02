package com.labdesoft.roteiro01.controller;

import com.labdesoft.roteiro01.dto.TaskDto;
import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.repository.TaskRepository;
import com.labdesoft.roteiro01.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class TaskController {

    private final TaskService taskService;
    @Autowired
    TaskRepository taskRepository;
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/task")
    @Operation(summary = "Lista todas as tarefas da lista")
    public ResponseEntity<List<Task>> listAll() {
        try {
            List<Task> taskList = new ArrayList<Task>();
            taskRepository.findAll().forEach(taskList::add);
            if (taskList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/task")
    @Operation(summary = "Adiciona uma nova tarefa à lista")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskDto) {
        try {
            Task newTask = taskService.createTask(taskDto);
            return new ResponseEntity<>(newTask, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/task/{taskId}")
    @Operation(summary = "Deleta uma tarefa da lista pelo ID")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long taskId) {
        try {
            taskRepository.deleteById(taskId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/task/{taskId}")
    @Operation(summary = "Atualiza uma tarefa da lista pelo ID")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task taskDetails) {
        try {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada com o ID: " + taskId));

            task.setDescription(taskDetails.getDescription());
            task.setCompleted(taskDetails.isCompleted());
            // Adicione outras atualizações conforme necessário

            Task updatedTask = taskRepository.save(task);
            return ResponseEntity.ok(updatedTask);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar a tarefa", e);
        }
    }

    @PutMapping("/task/{taskId}/complete")
    @Operation(summary = "Marca uma tarefa como concluída")
    public ResponseEntity<Task> completeTask(@PathVariable Long taskId) {
        try {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada com o ID: " + taskId));

            task.setCompleted(true); // Marcando a tarefa como concluída
            Task updatedTask = taskRepository.save(task);
            return ResponseEntity.ok(updatedTask);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao marcar a tarefa como concluída", e);
        }
    }

}

