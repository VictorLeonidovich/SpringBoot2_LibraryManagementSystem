package com.kvl.library.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BookRequestDTO {

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно быть длиной от 2 до 50 символов")
    private String name;

    @NotEmpty(message = "ISBN не должно быть пустым")
    @Size(min = 2, max = 50, message = "ISBN должно быть длиной от 2 до 50 символов")
    private String isbn;

    @NotEmpty(message = "Описание не должно быть пустым")
    @Size(min = 2, max = 250, message = "Описание должно быть длиной от 2 до 250 символов")
    private String description;

    // Receive collections of relationship relational IDs from the client
    private Set<Long> authorIds;
    private Set<Long> categoryIds;
    private Set<Long> publisherIds;
}
