package com.labdesoft.roteiro01.integration;

import com.labdesoft.roteiro01.Roteiro01Application;
import com.labdesoft.roteiro01.dto.TaskDto;
import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.entity.Task.TaskPriority;
import com.labdesoft.roteiro01.entity.Task.TaskType;
import com.labdesoft.roteiro01.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Roteiro01Application.class})
@ActiveProfiles("test")
public class TaskServiceIntegrationTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void givenNewTask_whenCreated_thenCorrect() {
        TaskDto taskDto = new TaskDto();
        taskDto.setDescription("Nova tarefa");
        taskDto.setType(TaskType.LIVRE);
        taskDto.setPriority(TaskPriority.ALTA);

        Task createdTask = taskService.createTask(taskDto);

        assertNotNull(createdTask.getId());
        assertEquals("Nova tarefa", createdTask.getDescription());
        assertEquals(TaskType.LIVRE, createdTask.getType());
        assertEquals(TaskPriority.ALTA, createdTask.getPriority());
    }

    @Test
    public void givenTaskId_whenDeleted_thenCorrect() {
        long taskId = 1; // ID da tarefa a ser deletada

        taskService.deleteTask(taskId);

        assertThrows(NoSuchElementException.class, () -> taskService.getTaskById(taskId));
    }

    @Test
    public void givenUpdatedTask_whenUpdated_thenCorrect() {
        long taskId = 1; // ID da tarefa a ser atualizada
        TaskDto updatedTaskDto = new TaskDto();
        updatedTaskDto.setDescription("Tarefa Atualizada");
        updatedTaskDto.setType(TaskType.PRAZO);
        updatedTaskDto.setPriority(TaskPriority.MEDIA);

        Task updatedTask = taskService.atualizarTask(taskId, updatedTaskDto);

        assertEquals("Tarefa Atualizada", updatedTask.getDescription());
        assertEquals(TaskType.PRAZO, updatedTask.getType());
        assertEquals(TaskPriority.MEDIA, updatedTask.getPriority());
    }

    @Test
    public void givenTaskId_whenMarkedAsCompleted_thenCorrect() {
        long taskId = 1; // ID da tarefa a ser marcada como concluída

        Task completedTask = taskService.concluidaTask(taskId);

        assertTrue(completedTask.getCompleted());
    }
}
