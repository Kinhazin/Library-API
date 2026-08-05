package io.github.kinhazin.libraryapi.repository;

import io.github.kinhazin.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
    @Query("SELECT a FROM Autor a WHERE a.nome = ?1 and a.nacionalidade =?2")
    List<Autor> findByNomeAndNacionalidade(String nome, String nacionalidade);

    List<Autor> findByNomeAndNacionalidadeAndDataNascimento(String nome, String nacionalidade, LocalDate dataNascimento);
    List<Autor> findByNome(String nome);
    List<Autor> findByNacionalidade(String Nacionalidade);
}
