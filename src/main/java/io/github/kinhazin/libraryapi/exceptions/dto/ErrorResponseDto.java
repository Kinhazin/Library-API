package io.github.kinhazin.libraryapi.exceptions.dto;

import io.github.kinhazin.libraryapi.exceptions.utils.ErrorFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Data
public class ErrorResponseDto {
    private HttpStatus codeResponse;
    private String message;
    private List<ErrorFields> errors;
}
