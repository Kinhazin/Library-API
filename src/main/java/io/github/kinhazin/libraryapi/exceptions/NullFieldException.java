package io.github.kinhazin.libraryapi.exceptions;

import java.util.List;

public class NullFieldException extends RuntimeException{
    public NullFieldException(List<String> fields){
        super("Os seguintes campos estão com valores nulos: %s".formatted(String.join(", ", fields)));
    }
}
