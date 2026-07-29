package io.github.kinhazin.libraryapi.controller.dto;

import io.github.kinhazin.libraryapi.model.Autor;

import java.time.LocalDate;
import java.util.UUID;

//Data Transfer Object -> Utilizado para trasnferência de dados (representação do objeto)
public record AutorDTO(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade
) {
    public Autor toAutor() {
        return Autor.builder().nome(nome)
                .dataNascimento(dataNascimento)
                .nacionalidade(nacionalidade).build();
    }

    public AutorDTO(Autor autor){
         this(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
    }
}
