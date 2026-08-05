package io.github.kinhazin.libraryapi.exceptions;

public class ResourceExistsException extends RuntimeException{
    public ResourceExistsException(String resource, String id){
        super("O %s de Id %s já existe".formatted(resource, id));
    }

}
