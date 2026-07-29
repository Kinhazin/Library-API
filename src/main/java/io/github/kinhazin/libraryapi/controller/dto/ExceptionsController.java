package io.github.kinhazin.libraryapi.controller.dto;

import io.github.kinhazin.libraryapi.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsController {

@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> handleNotFound(){
        return ResponseEntity.notFound().build();
    }
}
