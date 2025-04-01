package com.zamora.inventory.controller;

import com.zamora.inventory.exceptions.BadRequestException;
import com.zamora.inventory.exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("error", HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("error", HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            org.springframework.http.HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, Object> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Error de validación");
        body.put("details", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("timestamp", LocalDateTime.now());
        errors.put("message", "Error de validación");

        Map<String, String> violations = new LinkedHashMap<>();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : constraintViolations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            violations.put(fieldName, errorMessage);
        }
        errors.put("details", violations);

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
