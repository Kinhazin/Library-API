package io.github.kinhazin.libraryapi.controller;

import io.github.kinhazin.libraryapi.exceptions.dto.ErrorResponseDto;
import io.github.kinhazin.libraryapi.exceptions.*;
import io.github.kinhazin.libraryapi.exceptions.utils.ErrorFields;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFound(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(HttpStatus.NOT_FOUND, ex.getError(), List.of(new ErrorFields("Id", "Não cadastrado"))));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ErrorFields> listErros = ex.getFieldErrors().stream().map(e -> new ErrorFields(e.getField(), e.getDefaultMessage())).toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(HttpStatus.BAD_REQUEST, "Erro de validação", listErros));
    }

    @ExceptionHandler(ResourceExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceExists(ResourceExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDto(HttpStatus.CONFLICT, ex.getError(), null));
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidOperation(InvalidOperationException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(HttpStatus.BAD_REQUEST, ex.getMessage(), null) {
        });
    }
}


