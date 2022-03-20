package com.github.kazimbayram.groove.exceptions;

import com.github.kazimbayram.groove.model.ErrorModel;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorModel> resourceNotFoundException(Exception ex, WebRequest request) {

        var error = ErrorModel.builder().errorMessage(ex.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var bindingResult = ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));

        var error = ErrorModel.builder().errorMessage(bindingResult).statusCode(HttpStatus.BAD_REQUEST.value()).build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TopicNotFoundException.class})
    public ResponseEntity<ErrorModel> resourceNotFoundException(TopicNotFoundException ex) {

        var error = ErrorModel.builder().errorMessage(ex.getMessage()).statusCode(HttpStatus.NOT_FOUND.value()).build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ErrorModel> resourceNotFoundException(ValidationException ex) {

        var error = ErrorModel.builder().errorMessage(ex.getMessage()).statusCode(HttpStatus.BAD_REQUEST.value()).build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
