package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repo;

    @Override
    public TaskResponse createTask(TaskRequest req) {
        Task t = new Task();
        t.setTitle(req.title());
        t.setDescription(req.description());
        t.setDueDate(req.dueDate());
        Task saved = repo.save(t);
        return toResponse(saved);
    }

    @Override
    public TaskResponse getTask(Long id) {
        Task t = repo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return toResponse(t);
    }

    @Override
    public Page<TaskResponse> listTasks(Pageable pageable) {
        return repo.findAll(pageable).map(this::toResponse);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest req) {
        Task t = repo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        t.setTitle(req.title());
        t.setDescription(req.description());
        t.setDueDate(req.dueDate());
        t.setUpdatedAt(java.time.LocalDateTime.now());
        return toResponse(repo.save(t));
    }

    @Override
    public void deleteTask(Long id) {
        repo.deleteById(id);
    }

    private TaskResponse toResponse(Task t) {
        return new TaskResponse(t.getId(), t.getTitle(), t.getDescription(), t.getDueDate(), t.getStatus(), t.getCreatedAt(), t.getUpdatedAt());
    }
}
