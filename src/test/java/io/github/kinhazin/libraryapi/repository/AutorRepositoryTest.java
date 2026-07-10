package io.github.kinhazin.libraryapi.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.github.kinhazin.libraryapi.model.Autor;
import io.github.kinhazin.libraryapi.model.Livro;
import io.github.kinhazin.libraryapi.model.enums.GeneroLivros;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;


    @Test
    public void salvarAutor(){
        Autor autor = new Autor();
        autor.setNome("Maria Eduarda de Mello Pelegrini");
        autor.setDataNascimento(LocalDate.of(2004,  10, 22));
        autor.setNacionalidade("Brasileira");
        var autorSalvo = autorRepository.save(autor);

        System.out.println(autorSalvo);
    }

    @Test
    public void atualizar(){
        Autor autor = autorRepository.findById(UUID.fromString("a3aa8af7-1b08-4bfa-9e56-527770e33a17")).orElse(null);
        autor.setNome("Damalesco Samã de Daminhão");
        autorRepository.save(autor);
        System.out.println(autor);
    }

    @Test
    public void listasrTest(){
        autorRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void countDeAtores(){
        System.out.printf("Contagem de autores registrados no banco: %d \n", autorRepository.count());
    }

    @Test
    public void salvarAutorComLivro(){
        Autor autor = new Autor();
        autor.setNome("Marcos Eduardo");
        autor.setNacionalidade("Turco");
        autor.setDataNascimento(LocalDate.of(2005,2,10));

        Livro livro = new Livro();
        livro.setAutor(autor);
        livro.setTitulo("O cavaleiro das trevas");
        livro.setIsbn("19KFG3");
        livro.setGenero(GeneroLivros.FANTASIA);
        livro.setPreco(BigDecimal.valueOf(20000));
        livro.setDataPublicacao(LocalDate.of(1998, 3,20));

        Livro livro2 = new Livro();
        livro2.setAutor(autor);
        livro2.setTitulo("O cavaleiro das brancas");
        livro2.setIsbn("12223");
        livro2.setGenero(GeneroLivros.FANTASIA);
        livro2.setPreco(BigDecimal.valueOf(23300));
        livro2.setDataPublicacao(LocalDate.of(1998, 3,20));

        autor.getListaDeLivros().addAll(List.of(livro2,livro));

        autorRepository.save(autor);
    }

    @Test
    @Transactional
    void listarAutorELivros(){
        UUID id = UUID.fromString("d69de9bd-5603-4919-93da-71a183bf136a");
        Autor autor = autorRepository.findById(id).orElse(null);
        List<Livro> livros = livroRepository.findByAutorId(id);
        livros.forEach(System.out::println);
    }
}