package com.example.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record TaskRequest(
        @NotBlank String title,
        String description,
        LocalDateTime dueDate
) {}
