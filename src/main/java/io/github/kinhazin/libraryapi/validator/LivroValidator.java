package io.github.kinhazin.libraryapi.validator;

import io.github.kinhazin.libraryapi.exceptions.ResourceExistsException;
import io.github.kinhazin.libraryapi.model.Livro;
import io.github.kinhazin.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LivroValidator {

    private final LivroRepository livroRepository;


    public void validar(Livro livro) {
        if(existeLivroComIsbn(livro)) throw new ResourceExistsException("O ISBN:", livro.getIsbn());
    }

    private boolean existeLivroComIsbn(Livro livro) {
        var livroEncontrado = livroRepository.findByIsbn(livro.getIsbn()).orElse(null);
        if(livroEncontrado == null) return false;
        return !livroEncontrado.getId().equals(livro.getId());
    }
}
