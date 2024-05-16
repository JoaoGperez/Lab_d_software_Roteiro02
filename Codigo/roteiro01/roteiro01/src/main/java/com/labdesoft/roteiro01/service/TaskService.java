package com.labdesoft.roteiro01.service;

import com.labdesoft.roteiro01.dto.TaskDto;
import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.repository.TaskRepository;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> listAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Task createTask(TaskDto taskDto) {
        Task task = new Task();
        taskDto.setId(taskDto.getId());
        taskDto.setDescription(taskDto.getDescription());
        taskDto.setCompleted(false);
        taskDto.setType(taskDto.getType());
        taskDto.setPriority(taskDto.getPriority());

        switch (taskDto.getType()) {
            case DATA:
                task.setDueDate(taskDto.getDueDate());
                break;
            case PRAZO:
                task.setDaysToComplete(taskDto.getDaysToComplete());
                break;
            case LIVRE:
                task.setDueDate(null);
                task.setDaysToComplete(null);
        }

        return taskRepository.save(task);
    }

    public void deleteTask(long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Tarefa não encontrada"));
        taskRepository.deleteById(id);
    }

    public Task atualizarTask(long id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Tarefa não encontrada"));

        //Atualiza os campos da tarefa com os dados do DTO
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setType(taskDto.getType());
        existingTask.setPriority(taskDto.getPriority());

        return taskRepository.save(existingTask);
    }

    public Task concluidaTask(long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Tarefa não encontrada"));
        task.setCompleted(true);
        return taskRepository.save(task);
    }


}
