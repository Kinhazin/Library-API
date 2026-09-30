package io.github.kinhazin.libraryapi.validator;

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

    public void validarExists(Autor autor){
        List<Autor> autorExists = repository.findByNomeAndNacionalidadeAndDataNascimento(
                autor.getNome(),
                autor.getNacionalidade(),
                autor.getDataNascimento());
        if(!autorExists.isEmpty()) throw new ResourceExistsException("Autor", autorExists.getFirst().getId().toString());
    }


}
