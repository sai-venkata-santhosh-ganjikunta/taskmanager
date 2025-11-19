package com.example.taskmanager.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleNotFound(RuntimeException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse("error", ex.getMessage()));
    }

    record ErrorResponse(String status, String message) {}
}
