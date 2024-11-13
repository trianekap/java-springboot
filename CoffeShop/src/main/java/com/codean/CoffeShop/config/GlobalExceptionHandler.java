package com.codean.CoffeShop.config;

import com.codean.CoffeShop.exception.ResourceAlreadyExistException;
import com.codean.CoffeShop.exception.ResourceNotExistException;
import com.codean.CoffeShop.models.responses.ApiResponses;
import com.codean.CoffeShop.models.responses.ResponseMessages;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
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

}
