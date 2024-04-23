package io.dodn.springboot.core.api.config;

import com.fasterxml.jackson.databind.JsonMappingException;
import io.dodn.springboot.core.api.support.error.CoreApiException;
import io.dodn.springboot.core.api.support.error.ErrorType;
import io.dodn.springboot.core.api.support.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ApiExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder logMessage = new StringBuilder();
        StringBuilder responseMessage = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            logMessage.append("Valid fail field name : [").append(fieldError.getField()).append("] / Message : [").append(fieldError.getDefaultMessage()).append("]");
            responseMessage.append(fieldError.getDefaultMessage());
        }
        ErrorType errorType = ErrorType.VALID_ERROR;
        log.warn("CoreApiException : {}", logMessage, e);
        return new ResponseEntity<>(ApiResponse.error(errorType, responseMessage), errorType.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        ErrorType errorType = ErrorType.BAD_REQUEST;
        if (e.getCause() instanceof JsonMappingException jsonMappingException) {
            String errorMessage = jsonMappingException.getPathReference();
            int startIndex = errorMessage.indexOf('[');
            int endIndex = errorMessage.indexOf(']', startIndex);
            String errorField = errorMessage.substring(startIndex + 2, endIndex-1);
            return new ResponseEntity<>(ApiResponse.error(errorType, errorField + " 필드의 값이 잘못되었습니다."), errorType.getStatus());
        }
        return new ResponseEntity<>(ApiResponse.error(errorType), errorType.getStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String message = "Method argument type mismatch. Mismatch property name = " + e.getPropertyName();
        log.warn("CoreApiException : {}", message, e);
        ErrorType errorType = ErrorType.BAD_REQUEST;
        return new ResponseEntity<>(ApiResponse.error(errorType, message), errorType.getStatus());
    }

    @ExceptionHandler(CoreApiException.class)
    public ResponseEntity<ApiResponse<?>> handleCoreApiException(CoreApiException e) {
        switch (e.getErrorType().getLogLevel()) {
            case ERROR -> log.error("CoreApiException : {}", e.getMessage(), e);
            case WARN -> log.warn("CoreApiException : {}", e.getMessage(), e);
            default -> log.info("CoreApiException : {}", e.getMessage(), e);
        }
        return new ResponseEntity<>(ApiResponse.error(e.getErrorType(), e.getData()), e.getErrorType().getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        log.error("Exception : {}", e.getMessage(), e);
        return new ResponseEntity<>(ApiResponse.error(ErrorType.DEFAULT_ERROR), ErrorType.DEFAULT_ERROR.getStatus());
    }
}
