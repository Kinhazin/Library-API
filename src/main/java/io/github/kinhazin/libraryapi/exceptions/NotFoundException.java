package io.github.kinhazin.libraryapi.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String recurso, String id){
        super("O %s de id %s não foi localizado".formatted(recurso, id));
    }
}
