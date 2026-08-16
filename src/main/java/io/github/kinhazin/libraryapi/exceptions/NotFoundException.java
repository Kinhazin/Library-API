package io.github.kinhazin.libraryapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotFoundException extends RuntimeException {
    private String recurso;
    private String id;

    public String getError(){
        return "O %s de Id: %s não está cadastrado".formatted(recurso, id);
    }
}
