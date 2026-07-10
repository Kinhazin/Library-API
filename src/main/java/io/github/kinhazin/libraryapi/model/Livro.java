package io.github.kinhazin.libraryapi.model;

import io.github.kinhazin.libraryapi.model.enums.GeneroLivros;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Data
@ToString(exclude = "autor")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false, length = 30)
    private GeneroLivros genero;

    @Column(name = "preco", nullable = false, precision = 18, scale = 2)
    private BigDecimal preco;

    @ManyToOne(
//            cascade = CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH
            fetch = FetchType.LAZY
            ) // Atores podem ter 1 ou mais livros, mas livros só podem ter 1 autor
    @JoinColumn(name = "id_autor") // fazendo a relação entre livros e autor
    private Autor autor;

}
