package com.labdesoft.roteiro01.service;

import com.labdesoft.roteiro01.dto.TaskDto;
import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.repository.TaskRepository;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        taskDto.setDesctiption(taskDto.getDesctiption());
        taskDto.setCompleted(false);
        taskDto.setType(taskDto.getType());
        taskDto.setPriority(taskDto.getPriority());
        return taskRepository.save(task);
    }
}
