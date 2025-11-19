package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    TaskResponse createTask(TaskRequest req);
    TaskResponse getTask(Long id);
    Page<TaskResponse> listTasks(Pageable pageable);
    TaskResponse updateTask(Long id, TaskRequest req);
    void deleteTask(Long id);
}
