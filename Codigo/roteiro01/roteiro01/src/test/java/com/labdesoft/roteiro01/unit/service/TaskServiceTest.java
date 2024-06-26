package com.labdesoft.roteiro01.unit.service;

import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.mock.TaskMock;
import com.labdesoft.roteiro01.repository.TaskRepository;
import com.labdesoft.roteiro01.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    TaskRepository tasksRepository;
    private TaskService taskService;

    @BeforeEach
    public void setup() {
        taskService = new TaskService(tasksRepository);
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.desc("id")));

        Mockito.lenient().when(tasksRepository.findAll(pageable)).thenReturn(TaskMock.createTasks());
    }

    @Test
    @DisplayName("Should retun all tasks")
    public void should_list_all_tasks_repository() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.desc("id")));
        Page<Task> tasks = taskService.listAll(pageable);
        assertEquals(tasks.getTotalPages(), 1);
        assertEquals(tasks.getNumberOfElements(), 2);
        assertNotNull(tasks);
    }
}