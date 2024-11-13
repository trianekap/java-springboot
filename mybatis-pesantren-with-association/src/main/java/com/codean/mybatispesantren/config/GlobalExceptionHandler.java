package com.codean.mybatispesantren.config;

import com.codean.mybatispesantren.exceptions.ResourceNotExistException;
import com.codean.mybatispesantren.model.responses.ApiResponses;
import com.codean.mybatispesantren.model.responses.ResponsesMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<String>> HandleMethodArgumentNotValidExceptionError(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(ResourceNotExistException.class)
    public ApiResponses<Object> handleResourceNotFoundErrors(ResourceNotExistException ex) {
        String errorMessage = ex.getMessage();
        return ApiResponses.fail(HttpStatus.NOT_FOUND, ResponsesMessages.RESOURCE_NOTFOUND.getMessages(), errorMessage);
    }
}
