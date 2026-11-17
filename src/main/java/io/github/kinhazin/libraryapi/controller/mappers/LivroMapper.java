package io.github.kinhazin.libraryapi.controller.mappers;

import io.github.kinhazin.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.kinhazin.libraryapi.controller.dto.PesquisaLivroDTO;
import io.github.kinhazin.libraryapi.model.Livro;
import io.github.kinhazin.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java(autorRepository.findById(dto.idAutor()).orElse(null))" )
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract PesquisaLivroDTO toDto(Livro livro);
}
