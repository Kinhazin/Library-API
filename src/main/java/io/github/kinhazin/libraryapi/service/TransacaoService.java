package io.github.kinhazin.libraryapi.service;

import io.github.kinhazin.libraryapi.model.Autor;
import io.github.kinhazin.libraryapi.model.Livro;
import io.github.kinhazin.libraryapi.model.enums.GeneroLivros;
import io.github.kinhazin.libraryapi.repository.AutorRepository;
import io.github.kinhazin.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void executar(){
        Autor autor = Autor.builder().build();
        autor.setNome("Tulio Maravilha");
        autor.setDataNascimento(LocalDate.of(1973,  11, 14));
        autor.setNacionalidade("Brasileiro");
        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setAutor(autor);
        livro.setIsbn("77777");
        livro.setPreco(BigDecimal.valueOf(1045.87));
        livro.setGenero(GeneroLivros.MISTERIO);
        livro.setTitulo("DAGON BAL");
        livro.setDataPublicacao(LocalDate.of(2026, 6, 1));
        livroRepository.save(livro);

        if(autor.getNome().contains("Mario")) throw new RuntimeException(("Deu ruim patrão"));
    }

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository.findById(UUID.fromString("1a8fe940-b1aa-4c12-9fdc-98f3490e9c62")).orElse(null);
        livro.setDataPublicacao(LocalDate.of(2001,06,01));
    }


}
