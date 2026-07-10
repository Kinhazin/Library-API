package io.github.kinhazin.libraryapi.repository;

import io.github.kinhazin.libraryapi.model.Autor;
import io.github.kinhazin.libraryapi.model.Livro;
import io.github.kinhazin.libraryapi.model.enums.GeneroLivros;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    //Querry method
    List<Livro> findByAutorId(UUID id);
    List<Livro> findByTitulo(String titulo);
    List<Livro> findByGeneroAndPrecoGreaterThan(GeneroLivros genero, BigDecimal preco);

    @Query(" select l from Livro as l order by l.titulo")
    List<Livro> listarTodosLivros();

    @Query("Select a from Autor as a inner join Livro as l on a = l.autor")
    List<Autor> listarAutoresDosLivros();

    @Query("""
    select l 
        from Livro as l
            where l.genero  = :genero
    """)
    List<Livro> listarGeneros(@Param("genero") GeneroLivros generoLivros);

    @Modifying
    @Transactional
    @Query("Delete from Livro where genero =:genero")
    void deleteByGenero(@Param("genero") GeneroLivros genero);


}
