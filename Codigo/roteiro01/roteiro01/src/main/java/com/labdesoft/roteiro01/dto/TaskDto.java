package com.labdesoft.roteiro01.dto;

import com.labdesoft.roteiro01.entity.Task;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data

@Getter
@Setter
public class TaskDto {
    private long id;
    private String description;
    private boolean completed;
    private Task.TaskType type;
    private Task.TaskPriority priority;
    private LocalDate dueDate;
    private Integer daysToComplete;
    private String status;
}
