package com.kvl.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private String description;
}
