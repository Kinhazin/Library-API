package io.github.kinhazin.libraryapi.service;

import io.github.kinhazin.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.kinhazin.libraryapi.model.Livro;
import io.github.kinhazin.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro salvar(CadastroLivroDTO dto){
        return null;
    }

}
