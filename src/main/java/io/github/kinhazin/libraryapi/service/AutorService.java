package io.github.kinhazin.libraryapi.service;

import io.github.kinhazin.libraryapi.controller.dto.AutorDTO;
import io.github.kinhazin.libraryapi.exceptions.NotFoundException;
import io.github.kinhazin.libraryapi.model.Autor;
import io.github.kinhazin.libraryapi.repository.AutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AutorService {
    private final AutorRepository autorRepository;

    public Autor salvar(Autor autor){
        return autorRepository.save(autor);
    }

    public Autor buscaId(UUID id){
        return autorRepository.findById(id).orElseThrow(() -> new NotFoundException("Autor", id.toString()));
    }

    public void deleteId(UUID id) {
            Autor autor = autorRepository.findById(id).orElseThrow(() -> new NotFoundException("Autor", id.toString()));
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

        if(autorDTO.nome()!= null) autor.setNome(autorDTO.nome());
        if(autorDTO.nacionalidade()!= null) autor.setNacionalidade(autorDTO.nacionalidade());
        if(autorDTO.dataNascimento()!= null) autor.setDataNascimento(autorDTO.dataNascimento());
        autorRepository.save(autor);
    }


}
