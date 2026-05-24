package com.kvl.library.mapper;

import com.kvl.library.dto.AuthorRequestDTO;
import com.kvl.library.dto.AuthorResponseDTO;
import com.kvl.library.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorResponseDTO toResponseDTO(Author author);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Author toEntity(AuthorRequestDTO authorRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateEntityFromDto(AuthorRequestDTO authorRequestDTO, @MappingTarget Author author);
}
