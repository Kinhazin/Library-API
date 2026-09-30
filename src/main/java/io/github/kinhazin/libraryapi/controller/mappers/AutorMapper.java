package io.github.kinhazin.libraryapi.controller.mappers;
import io.github.kinhazin.libraryapi.controller.dto.AutorDTO;
import io.github.kinhazin.libraryapi.model.Autor;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring" )
public interface AutorMapper {

    AutorDTO toDto(Autor auto);
    Autor toEntity(AutorDTO dto);
}
