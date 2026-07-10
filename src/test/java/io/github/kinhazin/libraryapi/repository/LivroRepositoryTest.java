package io.github.kinhazin.libraryapi.repository;

import io.github.kinhazin.libraryapi.model.Autor;
import io.github.kinhazin.libraryapi.model.Livro;
import io.github.kinhazin.libraryapi.model.enums.GeneroLivros;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;
    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setAutor(autorRepository.findAll().getFirst());
        livro.setIsbn("14953");
        livro.setPreco(BigDecimal.valueOf(200.00));
        livro.setGenero(GeneroLivros.MISTERIO);
        livro.setTitulo("Sherlock Holmes");
        livro.setDataPublicacao(LocalDate.of(2020, 2, 10));
        livroRepository.save(livro);

    }
    @Test
    @Transactional
    void salvarCascatedTest(){
        Autor autor = new Autor();
        autor.setNome("Sillas Malafaria");
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
    }

    @Test
    void atualizarAutorDoLivro(){
        Livro livro = livroRepository.findById(UUID.fromString("016d815c-348f-4cab-88c3-2a124c7a205a")).orElse(null);
        Autor autor = livro.getAutor();
        autor.setNome(autor.getNome() + " é muy bela");
        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void buscarAutorPeloLivro(){
        Livro livro = livroRepository.findById(UUID.fromString("016d815c-348f-4cab-88c3-2a124c7a205a")).orElse(null);
        Autor autor = livro.getAutor();
        System.out.print("O autor do livro: " + livro.getTitulo() + " é o: " + autor.getNome() + "\n");
    }

    @Test
    void pesquisaPorTituloTest(){
        var livros = livroRepository.findByTitulo("Biblia sagrada");
        livros.forEach(System.out::println);
    }

    @Test
    void pesquisarPorGeneroEPreco(){
        var livros = livroRepository.findByGeneroAndPrecoGreaterThan(GeneroLivros.FANTASIA, BigDecimal.valueOf(20000));
        livros.forEach(System.out::println);
    }

    @Test
    void ordenarLivrosPorTitulo(){
        var livro = livroRepository.listarTodosLivros();
        livro.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros(){
        var autores = livroRepository.listarAutoresDosLivros();
        autores.forEach(System.out::println);
    }

    @Test
    void listarGeneroDosLivros(){
        var generos = livroRepository.listarGeneros(GeneroLivros.MISTERIO);
        generos.forEach(System.out::println);
    }

    @Test
    void deleteByGenero(){
        livroRepository.deleteByGenero(GeneroLivros.MISTERIO);
    }



}