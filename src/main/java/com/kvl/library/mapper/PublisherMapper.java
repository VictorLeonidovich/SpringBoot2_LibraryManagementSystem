package com.kvl.library.mapper;

import com.kvl.library.dto.PublisherRequestDTO;
import com.kvl.library.dto.PublisherResponseDTO;
import com.kvl.library.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    PublisherResponseDTO toResponseDTO(Publisher publisher);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Publisher toEntity(PublisherRequestDTO publisherRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateEntityFromDto(PublisherRequestDTO publisherRequestDTO, @MappingTarget Publisher publisher);
}
