package io.github.kinhazin.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "autors", schema = "public")
@ToString(exclude = "listaDeLivros")
public class Autor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL, fetch = FetchType.LAZY) //Diz que a entidade não possui essa coluna
    // e o nome no mappedBy é para relacionar com o nome da propriedade na outra entidade
    private List<Livro> listaDeLivros = new ArrayList<>();

}
