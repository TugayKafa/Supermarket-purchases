package com.endava.Supermarket.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<CustomErrorResponse> customHandleCustomRuntimeException(Exception exception) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimeStamp(LocalDateTime.now());
        errors.setError(exception.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, Object> body = fillBody(status, ex);
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex,
                                                         HttpHeaders headers,
                                                         HttpStatus status, WebRequest request) {

        Map<String, Object> body = fillBody(status, ex, request);
        return new ResponseEntity<>(body, headers, status);
    }

    private Map<String, Object> fillBody(HttpStatus status, BindException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status);
        // request body parameter if is more or equal than 9 will execute for valid annotation validations.
        // in other case will get error message for filtered, sorted and paginated.
        if (request.getParameterMap().size() >= 9) {
            body.put("errors", getFieldErrorsForValid(ex));
        } else {
            body.put("errors", getFieldErrors(ex));
        }
        return body;
    }

    private Map<String, List<String>> getFieldErrorsForValid(BindException ex) {
        Map<String, List<String>> fieldErrors = new HashMap<>();
        ex.getFieldErrors().forEach(fieldError -> {
            fieldErrors.putIfAbsent(fieldError.getField(), new ArrayList<>());
            fieldErrors.get(fieldError.getField()).add(fieldError.getDefaultMessage());
        });
        return fieldErrors;
    }

    private Map<String, List<String>> getFieldErrors(BindException ex) {

        Map<String, List<String>> fieldErrors = new HashMap<>();
        ex.getFieldErrors().forEach(fieldError -> {
            fieldErrors.putIfAbsent(fieldError.getField(), new ArrayList<>());
            fieldErrors.get(fieldError.getField()).add(fieldError.getRejectedValue() + " is not valid for field " + fieldError.getField());
        });
        return fieldErrors;
    }

    private Map<String, Object> fillBody(HttpStatus status, MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status);
        body.put("errors", getFieldErrorsForValid(ex));
        return body;
    }

}
