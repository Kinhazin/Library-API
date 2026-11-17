package io.github.kinhazin.libraryapi.controller;

import io.github.kinhazin.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.kinhazin.libraryapi.controller.dto.PesquisaLivroDTO;
import io.github.kinhazin.libraryapi.controller.mappers.LivroMapper;
import io.github.kinhazin.libraryapi.controller.utils.GenericController;
import io.github.kinhazin.libraryapi.model.Livro;
import io.github.kinhazin.libraryapi.model.enums.GeneroLivros;
import io.github.kinhazin.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;


    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto){
        Livro livro = service.salvar(dto);

        URI location = gerarHeadLocation(livro.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PesquisaLivroDTO> buscarLivro(@PathVariable UUID id){
        Livro livro = service.buscar(id);
        if(livro != null) return ResponseEntity.ok().body(mapper.toDto(livro));
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<Page<PesquisaLivroDTO>> buscarLivroFiltro(
            @RequestParam(value = "isbn", required = false)
            String isbn,
            @RequestParam(value = "titulo", required = false)
            String titulo,
            @RequestParam(value = "genero", required = false)
            GeneroLivros genero,
            @RequestParam(value = "ano", required = false)
            Integer ano,
            @RequestParam(value = "nome-autor", required = false)
            String nomeAutor,
            @RequestParam(value = "page", defaultValue = "0")
            Integer page,
            @RequestParam(value = "size", defaultValue = "10")
            Integer size
    ){

        Page<Livro> livros = service.buscarPorFiltro(isbn, titulo, genero, ano, nomeAutor, page, size);

        return ResponseEntity.ok().body(livros.map(mapper::toDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarLivro(
            @PathVariable String id,
            @RequestBody(required = true) @Valid CadastroLivroDTO livro
    ) {
        service.atualizar(livro, id);

        return ResponseEntity.ok().build();
    }

}
