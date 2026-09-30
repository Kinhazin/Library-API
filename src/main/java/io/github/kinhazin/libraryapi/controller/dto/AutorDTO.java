package io.github.kinhazin.libraryapi.controller.dto;

import io.github.kinhazin.libraryapi.model.Autor;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

//Data Transfer Object -> Utilizado para trasnferência de dados (representação do objeto)
public record AutorDTO(
        UUID id,

        @NotBlank(message = "Campo obrigatório")
        @Size(max = 100, message = "Campo fora do tamanho padrão")
        String nome,

        @NotNull(message = "Campo obrigatório")
        @Past(message = "Não pode ser uma data futura")
        LocalDate dataNascimento,

        @Size(max = 50, message = "Campo fora do tamanho padrão")
        @NotBlank(message = "Campo obrigatório")
        String nacionalidade
) {

    public AutorDTO(Autor autor){
         this(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
    }
}
