package io.github.kinhazin.libraryapi.controller.dto;

import io.github.kinhazin.libraryapi.model.enums.GeneroLivros;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(
        @ISBN
        @NotBlank(message = "Campo obrigatório")
        String isbn,
        @NotBlank(message = "Campo obrigatório")
        String titulo,
        @NotNull(message = "Campo obrigatório")
        @Past(message = "Não pode ser uma data futura")
        LocalDate dataPublicacao,
        GeneroLivros genero,
        BigDecimal preco,
        @NotNull(message = "Campo obrigatório")
        UUID idAutor
) {
}

