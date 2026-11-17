package io.github.kinhazin.libraryapi.service;

import io.github.kinhazin.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.kinhazin.libraryapi.controller.mappers.LivroMapper;
import io.github.kinhazin.libraryapi.exceptions.NotFoundException;
import io.github.kinhazin.libraryapi.model.Livro;
import io.github.kinhazin.libraryapi.model.enums.GeneroLivros;
import io.github.kinhazin.libraryapi.repository.LivroRepository;
import io.github.kinhazin.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;
import static io.github.kinhazin.libraryapi.repository.specs.LivroSpecification.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroMapper mapper;
    private final LivroRepository repository;
    private final LivroValidator validator;

    public Livro salvar(CadastroLivroDTO dto){
        Livro livro = mapper.toEntity(dto);
        validator.validar(livro);
        return repository.save(livro);
    }

    public Livro buscar(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("livro", id.toString()));
    }

    public void deletar(UUID id){
        if(!repository.existsById(id)) throw new NotFoundException("livro",id.toString());
        repository.deleteById(id);
    }

    public Page<Livro> buscarPorFiltro(String isbn, String titulo, GeneroLivros genero, Integer ano, String nomeAutor, Integer page, Integer size){

        Specification<Livro> specs = Specification.where(((root, query, cb) -> cb.conjunction()));
        if(isbn != null) specs = specs.and(isbnEqual(isbn));
        if(titulo != null) specs = specs.and(tituloLike(titulo));
        if(genero != null) specs = specs.and(generoEqual(genero));
        if(ano != null) specs = specs.and(anoEquals(ano));

        Pageable pageable = PageRequest.of(page, size);

        return repository.findAll(specs, pageable);
    }

    public void atualizar (CadastroLivroDTO dto, String id){
        Livro entityLivro = mapper.toEntity(dto);
        entityLivro.setId(UUID.fromString(id));
        validator.validar(entityLivro);

        Livro livroBanco = buscar(UUID.fromString(id));
        livroBanco.setDataPublicacao(entityLivro.getDataPublicacao());
        livroBanco.setPreco(entityLivro.getPreco());
        livroBanco.setGenero(entityLivro.getGenero());
        livroBanco.setIsbn(entityLivro.getIsbn());
        livroBanco.setTitulo(entityLivro.getTitulo());


        repository.save(livroBanco);


    }

}
