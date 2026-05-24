package com.kvl.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BookResponseDTO {
    private Long id;
    private String name;
    private String isbn;
    private String description;

    // Flattened structural references to avoid deep recursive loops
    private Set<AuthorResponseDTO> authors;
    private Set<CategoryResponseDTO> categories;
    private Set<PublisherResponseDTO> publishers;
}
