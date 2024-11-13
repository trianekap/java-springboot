package com.codean.topup.config;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.responses.ApiResponse;
import com.codean.topup.models.responses.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> exceptionError(Exception ex){
        String errorMsg = ex.getMessage();

        logger.error(errorMsg);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ApiResponse<String> handleUsernameNotFoundException(InternalAuthenticationServiceException ex){
        String errorMessage = ex.getMessage();
        return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages(), errorMessage);
    }

    @ExceptionHandler(ResourceNotExistException.class)
    public ApiResponse<String> handleResourceNotFoundErrors(ResourceNotExistException ex) {
        String errorMessage = ex.getMessage();
        return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages(), errorMessage);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiResponse<String> handleUsernameNotFoundException(UsernameNotFoundException ex){
        String errorMessage = ex.getMessage();
        return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages(), errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse<String> handleConstraintErrors(ConstraintViolationException ex) {
        String errorMessage = ex.getCause().getMessage();
        return ApiResponse.fail(HttpStatus.BAD_REQUEST, ResponseMessage.BAD_REQUEST.getMessages(), errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<String>> handleMethodArgumentNotValidExceptionError(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        errors.forEach(logger::error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ResourceAlreadyException.class)
    public ApiResponse<Object> handleResourceExisted(ResourceAlreadyException e){
        String errorMessage = e.getMessage();
        return ApiResponse.fail(HttpStatus.CONFLICT, ResponseMessage.RESOURCE_ALREADY_EXIST.getMessages(), errorMessage);
    }

}
