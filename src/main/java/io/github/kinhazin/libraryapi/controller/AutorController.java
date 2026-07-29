package io.github.kinhazin.libraryapi.controller;

import io.github.kinhazin.libraryapi.controller.dto.AutorDTO;
import io.github.kinhazin.libraryapi.model.Autor;
import io.github.kinhazin.libraryapi.service.AutorService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("autores")
@AllArgsConstructor
//http://localhost:8080/autores
public class AutorController {
    private final AutorService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AutorDTO autorDto){
        Autor autor = autorDto.toAutor();
        service.salvar(autor);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autor.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> getDetails(@PathVariable UUID id){
        Autor autor = (service.buscaId(id));
        return ResponseEntity.ok(new AutorDTO(autor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.deleteId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<AutorDTO>> getAutors(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade){
            List<AutorDTO> lista =  service.getAutors(nome, nacionalidade).stream().map(a -> new AutorDTO(a)).collect(Collectors.toList());
            return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") UUID id, @RequestBody AutorDTO autorAtualizado){
       service.atualizar(id, autorAtualizado);
       return ResponseEntity.noContent().build();
    }

}
