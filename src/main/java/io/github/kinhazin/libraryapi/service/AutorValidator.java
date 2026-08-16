package io.github.kinhazin.libraryapi.service;

import io.github.kinhazin.libraryapi.controller.dto.AutorDTO;
import io.github.kinhazin.libraryapi.exceptions.ResourceExistsException;
import io.github.kinhazin.libraryapi.model.Autor;
import io.github.kinhazin.libraryapi.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AutorValidator {
    private final AutorRepository repository;

    public void validarExists(AutorDTO autorDTO){
        List<Autor> autorExists = repository.findByNomeAndNacionalidadeAndDataNascimento(
                autorDTO.nome(),
                autorDTO.nacionalidade(),
                autorDTO.dataNascimento());
        if(!autorExists.isEmpty()) throw new ResourceExistsException("Autor", autorExists.getFirst().getId().toString());
    }


}
