package com.proyectofs1.moderacion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Maneja las excepciones de la lógica de negocio lanzadas desde el Service
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Operación no válida");
        response.put("detalle", ex.getMessage());
        
        // Retorna un código 400 Bad Request con el mensaje específico
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 2. Maneja los errores de validación de Bean Validation (JSR 380) del DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        
        // Extrae exactamente qué campo falló y su mensaje configurado en el DTO
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errores.put(fieldName, errorMessage);
        });
        
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}