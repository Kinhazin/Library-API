package io.github.kinhazin.libraryapi.controller;

import io.github.kinhazin.libraryapi.exceptions.InvalidOperationException;
import io.github.kinhazin.libraryapi.exceptions.NotFoundException;
import io.github.kinhazin.libraryapi.exceptions.NullFieldException;
import io.github.kinhazin.libraryapi.exceptions.ResourceExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFound(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(ResourceExistsException.class)
    public ResponseEntity<ProblemDetail> handleResourceExists(ResourceExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(NullFieldException.class)
    public ResponseEntity<ProblemDetail> handleNullField(NullFieldException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ProblemDetail> handleInvalidOperation(InvalidOperationException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage()));
    }
}


