package com.codean.keuangan.configs;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<String>> HandleMethodArgumentNotValidExceptionError(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(ResourceNotExistException.class)
    ResponseEntity<String> handleResourceNotExistExceptionError(ResourceNotExistException ex){
        String errorMsg = ex.getMessage();

        logger.error(errorMsg);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> ExceptionError(Exception ex){
        String errorMsg = ex.getMessage();

        logger.error(errorMsg);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
    }


}
