package ru.geekbrains.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.geekbrains.exceptions.NotFoundException;
import ru.geekbrains.exceptions.ServerInternalException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<Void> notFoundExceptionHandler(NotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Void> internalServerExceptionHandler(ServerInternalException ex) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
