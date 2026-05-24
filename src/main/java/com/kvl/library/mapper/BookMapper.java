package com.kvl.library.mapper;

import com.kvl.library.dto.BookRequestDTO;
import com.kvl.library.dto.BookResponseDTO;
import com.kvl.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, CategoryMapper.class, PublisherMapper.class})
public interface BookMapper {

    // Converts Book + associated entity sets into structured BookResponseDTO payload
    BookResponseDTO toResponseDTO(Book book);

    // Structural entities collections must be resolved via services inside the controller layer
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "publishers", ignore = true)
    Book toEntity(BookRequestDTO bookRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "publishers", ignore = true)
    void updateEntityFromDto(BookRequestDTO bookRequestDTO, @MappingTarget Book book);
}
