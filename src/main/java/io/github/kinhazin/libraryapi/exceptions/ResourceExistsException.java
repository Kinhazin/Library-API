package io.github.kinhazin.libraryapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceExistsException extends RuntimeException{
    private String resource;
    private String id;

    public String getError(){
        return "%s de id: %s já cadastrado".formatted(resource, id);
    }


}
