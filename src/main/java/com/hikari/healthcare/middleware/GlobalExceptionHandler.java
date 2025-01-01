package com.hikari.healthcare.middleware;

import java.time.LocalDateTime;

import com.hikari.healthcare.common.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hikari.healthcare.model.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler({
            ResourceNotFoundException.class,
            ResourceNotFoundException.class,
            RoleNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleResourceNotFoundException(
            //menggunakan runtime exception karena menggunakan exception handler lebih dari 1
        HttpServletRequest request, RuntimeException exception
    ) {
        return ErrorResponse.builder()
            .code(HttpStatus.NOT_FOUND.value())
            .message(exception.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleBadRequestException(
        HttpServletRequest request, BadRequestException exception
    ) {
        return ErrorResponse.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .message(exception.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ErrorResponse handleUnauthorizeException(
            HttpServletRequest request, Exception exception
    ) {
        return ErrorResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({
            UsernameAlreadyExistException.class,
            EmailAlreadyExistException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ErrorResponse handleConflictException(
            HttpServletRequest request, Exception exception
    ) {
        return ErrorResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse handleGenericException(
        HttpServletRequest request, Exception exception
    ) {
        log.error("Telah terjadi error pada endpoint {}. status code: {}. error_message: {}",
            request.getRequestURI(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            exception.getMessage()
        );

        return ErrorResponse.builder()
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(exception.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
    }
    
}
    