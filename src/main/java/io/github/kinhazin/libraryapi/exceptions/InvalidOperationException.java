package io.github.kinhazin.libraryapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidOperationException extends RuntimeException{
    String message;
}
