package com.labdesoft.roteiro01.dto;

import com.labdesoft.roteiro01.entity.Task;
import lombok.Data;

@Data
public class TaskDto {
    private long id;
    private String desctiption;
    private boolean completed;
    private Task.TaskType type;
    private Task.TaskPriority priority;
}
