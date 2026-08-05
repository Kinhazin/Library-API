package io.github.kinhazin.libraryapi.service;

import io.github.kinhazin.libraryapi.controller.dto.AutorDTO;
import io.github.kinhazin.libraryapi.exceptions.InvalidOperationException;
import io.github.kinhazin.libraryapi.exceptions.NotFoundException;
import io.github.kinhazin.libraryapi.model.Autor;
import io.github.kinhazin.libraryapi.repository.AutorRepository;
import io.github.kinhazin.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {
    private final AutorRepository autorRepository;
    private final AutorValidator autorValidator;
    private final LivroRepository livroRepository;

    public Autor salvar(AutorDTO newAutor){
        autorValidator.validarExists(newAutor);
        autorValidator.validarParametros(newAutor);

        Autor autor = newAutor.toAutor();
        autorRepository.save(autor);
        return autor;
    }

    public Autor buscaId(UUID id){
        return autorRepository.findById(id).orElseThrow(() -> new NotFoundException("Autor", id.toString()));
    }

    public void deleteId(UUID id) {
            Autor autor = autorRepository.findById(id).orElseThrow(() -> new NotFoundException("Autor", id.toString()));
            if(!livroRepository.findByAutorId(autor.getId()).isEmpty()) throw new InvalidOperationException("Não é permetido excluir um autor com livro cadastrado");

            autorRepository.delete(autor);
    }

    public List<Autor> getAutors(String nome, String nacionalidade){
        if(nome != null && nacionalidade != null) return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        if(nome != null) return autorRepository.findByNome(nome);
        if(nacionalidade != null) return autorRepository.findByNacionalidade(nacionalidade);
        return autorRepository.findAll();
    }

    public void atualizar(UUID id, AutorDTO autorDTO){
        Autor autor = autorRepository.findById(id).orElseThrow(() -> new NotFoundException("Autor", id.toString()));
        autorValidator.validarParametros(autorDTO);

        autor.setNome(autorDTO.nome());
        autor.setNacionalidade(autorDTO.nacionalidade());
        autor.setDataNascimento(autorDTO.dataNascimento());
        autorRepository.save(autor);
    }


}
