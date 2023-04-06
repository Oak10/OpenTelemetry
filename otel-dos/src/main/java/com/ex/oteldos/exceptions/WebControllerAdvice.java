package com.ex.oteldos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler(WebMessageNotFoundException.class)
    public ResponseEntity<Object> handleWebMessageNotFoundException(WebMessageNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WebMessageInternalError.class)
    public ResponseEntity<Object> handleWebMessageInternalErrorException(WebMessageInternalError exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
