package com.kvl.library.controller;

import com.kvl.library.dto.PublisherRequestDTO;
import com.kvl.library.dto.PublisherResponseDTO;
import com.kvl.library.entity.Publisher;
import com.kvl.library.mapper.PublisherMapper;
import com.kvl.library.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/publishers")
@RequiredArgsConstructor
public class PublisherRestController {

    private final PublisherService publisherService;
    private final PublisherMapper publisherMapper;

    // GET /api/v1/publishers
    @GetMapping
    public ResponseEntity<List<PublisherResponseDTO>> getAllPublishers() {
        List<PublisherResponseDTO> publishers = publisherService.findAllPublishers().stream()
                .map(publisherMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(publishers);
    }

    // GET /api/v1/publishers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> getPublisherById(@PathVariable Long id) {
        Publisher publisher = publisherService.findPublisherById(id);
        return ResponseEntity.ok(publisherMapper.toResponseDTO(publisher));
    }

    // POST /api/v1/publishers
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PublisherResponseDTO> createPublisher(@Valid @RequestBody PublisherRequestDTO requestDTO) {
        Publisher publisher = publisherMapper.toEntity(requestDTO);
        publisherService.createPublisher(publisher);
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherMapper.toResponseDTO(publisher));
    }

    // PUT /api/v1/publishers/{id}
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PublisherResponseDTO> updatePublisher(@PathVariable Long id,
                                                                @Valid @RequestBody PublisherRequestDTO requestDTO) {
        Publisher existingPublisher = publisherService.findPublisherById(id);
        publisherMapper.updateEntityFromDto(requestDTO, existingPublisher);
        publisherService.updatePublisher(existingPublisher);
        return ResponseEntity.ok(publisherMapper.toResponseDTO(existingPublisher));
    }

    // DELETE /api/v1/publishers/{id}
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}
