package io.github.kinhazin.libraryapi.controller.dto;

import io.github.kinhazin.libraryapi.model.enums.GeneroLivros;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record PesquisaLivroDTO(
        String isbn,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivros genero,
        BigDecimal preco,
        UUID idAutor,
        AutorDTO autorDto
) {
}
