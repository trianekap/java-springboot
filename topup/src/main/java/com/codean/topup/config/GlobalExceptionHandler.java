package com.codean.topup.config;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.responses.ApiResponse;
import com.codean.topup.models.responses.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> ExceptionError(Exception ex){
        String errorMsg = ex.getMessage();

        logger.error(errorMsg);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
    }

    @ExceptionHandler(ResourceNotExistException.class)
    public ApiResponse<Object> handleResourceNotFoundErrors(ResourceNotExistException ex) {
        String errorMessage = ex.getMessage();
        return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages(), errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse<Object> handleConstraintErrors(ConstraintViolationException ex) {
        String errorMessage = ex.getCause().getMessage();
        return ApiResponse.fail(HttpStatus.BAD_REQUEST, ResponseMessage.BAD_REQUEST.getMessages(), errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> handleMethodArgumentNotValidErrors(MethodArgumentNotValidException ex){
        String errorMessage = ex.getMessage();
        return ApiResponse.fail(HttpStatus.BAD_REQUEST, ResponseMessage.BAD_REQUEST.getMessages(), errorMessage);
    }

    @ExceptionHandler(ResourceAlreadyException.class)
    public ApiResponse<Object> handleResourceExisted(ResourceAlreadyException e){
        String errorMessage = e.getMessage();
        return ApiResponse.fail(HttpStatus.CONFLICT, ResponseMessage.RESOURCE_ALREADY_EXIST.getMessages(), errorMessage);
    }
}
