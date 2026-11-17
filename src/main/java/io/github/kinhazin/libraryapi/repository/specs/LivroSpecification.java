package io.github.kinhazin.libraryapi.repository.specs;

import io.github.kinhazin.libraryapi.model.Livro;
import io.github.kinhazin.libraryapi.model.enums.GeneroLivros;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpecification {

    public static Specification<Livro> isbnEqual(String isbn){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isbn"), isbn);
    }

    public static Specification<Livro> tituloLike(String titulo){
        return ((root, query, cb) -> cb.like(cb.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%"));
    }

    public static Specification<Livro> generoEqual(GeneroLivros genero) {
        return (root, query, cb) -> cb.equal(root.get("genero"), genero);
    }

    public static Specification<Livro> anoEquals(Integer ano) {
        return (root, query, cb) ->  cb.like( cb.function("to_char", String.class, root.get("dataPublicacao"), cb.literal("YYYY")) , "%" + ano.toString());
    }

    public static Specification<Livro> nomeAutorLike(String nomeAutor) {
        return (root, query, cb) ->  {
            Join<Object, Object> joinAutor = root.join("autor", JoinType.LEFT);
            joinAutor.get("nome");
            return cb.like(cb.upper(joinAutor.get("nome")), "%" + nomeAutor.toUpperCase() + "%");

            // return cb.like(cb.upper(root.get("autor").get("nome")),"%" + nomeAutor.toUpperCase()  + "%");
        };
    }


}
