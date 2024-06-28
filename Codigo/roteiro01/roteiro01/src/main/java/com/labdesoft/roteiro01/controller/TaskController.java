package com.labdesoft.roteiro01.controller;

import com.labdesoft.roteiro01.dto.TaskDto;
import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.repository.TaskRepository;
import com.labdesoft.roteiro01.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(summary = "Lista todas as tarefas da lista")
    public ResponseEntity<Page<Task>> listAll(Pageable pageable) {
        try {
            Page<Task> tasksPage = taskService.listAll(pageable);
            if (tasksPage.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tasksPage, HttpStatus.OK);
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


    @DeleteMapping("/{taskId}")
    @Operation(summary = "Deleta uma tarefa da lista pelo ID")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{taskId}")
    @Operation(summary = "Atualiza uma tarefa da lista pelo ID")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        try {
            Task updateTask = taskService.atualizarTask(taskId, taskDto);
            return ResponseEntity.ok(updateTask);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{taskId}/complete")
    @Operation(summary = "Marca uma tarefa como concluída")
    public ResponseEntity<Task> completeTask(@PathVariable Long taskId) {
        try {
            Task completedTask = taskRepository.findById(taskId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada com o ID: " + taskId));

            completedTask.setCompleted(true); // Marcando a tarefa como concluída
            Task updatedTask = taskRepository.save(completedTask);
            return ResponseEntity.ok(updatedTask);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao marcar a tarefa como concluída", e);
        }
    }

    @GetMapping("/{taskId}")
    @Operation(summary = "Recuperar tarefa pelo ID")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long taskId) {
        try {
            Task task = taskService.getTaskById(taskId);
            TaskDto taskDto = new TaskDto();
            taskDto.setDescription(task.getDescription());
            taskDto.setCompleted(task.getCompleted());
            taskDto.setType(task.getType());
            taskDto.setPriority(task.getPriority());
            taskDto.setDueDate(task.getDueDate());
            taskDto.setDaysToComplete(task.getDaysToComplete());
            taskDto.setStatus(task.getStatus());
            return ResponseEntity.ok(taskDto);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
