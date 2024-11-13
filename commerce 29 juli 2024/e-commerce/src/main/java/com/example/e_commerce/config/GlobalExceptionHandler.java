package com.example.e_commerce.config;

import com.example.e_commerce.exceptions.InvalidFilterTypeException;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.responses.ApiResponses;
import com.example.e_commerce.model.responses.ResponseMessages;
import io.swagger.annotations.ApiResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ApiResponses<Object> handleResourceExisted(ResourceAlreadyExistException e){
        String errorMessage = e.getMessage();
        return ApiResponses.fail(HttpStatus.CONFLICT, ResponseMessages.RESOURCE_ALREADY_EXIST.getMessages(), errorMessage);
    }

    @ExceptionHandler(ResourceNotExistException.class)
    public ApiResponses<Object> handleResourceNotFoundErrors(ResourceNotExistException ex) {
        String errorMessage = ex.getMessage();
        return ApiResponses.fail(HttpStatus.NOT_FOUND, ResponseMessages.RESOURCE_NOTFOUND.getMessages(), errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponses<Object> handleConstraintErrors(ConstraintViolationException ex) {
        String errorMessage = ex.getCause().getMessage();
        return ApiResponses.fail(HttpStatus.BAD_REQUEST, ResponseMessages.BAD_REQUEST.getMessages(), errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponses<Object> handleMethodArgumentNotValidErrors(MethodArgumentNotValidException ex){
        String errorMessage = ex.getMessage();
        return ApiResponses.fail(HttpStatus.BAD_REQUEST, ResponseMessages.BAD_REQUEST.getMessages(), errorMessage);
    }

    @ExceptionHandler(InvalidFilterTypeException.class)
    public ApiResponses<Object> handleInvalidSortTpeErrors(InvalidFilterTypeException ex) {
        String errorMsg = ex.getMessage();
        return ApiResponses.fail(HttpStatus.BAD_REQUEST, ResponseMessages.BAD_REQUEST.getMessages(), errorMsg);
    }



}
