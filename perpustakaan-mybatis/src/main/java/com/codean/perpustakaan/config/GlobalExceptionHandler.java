package com.codean.perpustakaan.config;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<String>> HandleMethodArgumentNotValidExceptionError(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }
}
