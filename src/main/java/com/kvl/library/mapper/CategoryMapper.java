package com.kvl.library.mapper;

import com.kvl.library.dto.CategoryRequestDTO;
import com.kvl.library.dto.CategoryResponseDTO;
import com.kvl.library.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDTO toResponseDTO(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Category toEntity(CategoryRequestDTO categoryRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateEntityFromDto(CategoryRequestDTO categoryRequestDTO, @MappingTarget Category category);
}