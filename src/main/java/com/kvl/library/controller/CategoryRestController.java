package com.kvl.library.controller;

import com.kvl.library.dto.CategoryRequestDTO;
import com.kvl.library.dto.CategoryResponseDTO;
import com.kvl.library.entity.Category;
import com.kvl.library.mapper.CategoryMapper;
import com.kvl.library.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    // GET /api/v1/categories
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categories = categoryService.findAllCategories().stream()
                .map(categoryMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }

    // GET /api/v1/categories/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findCategoryById(id);
        return ResponseEntity.ok(categoryMapper.toResponseDTO(category));
    }

    // POST /api/v1/categories
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO) {
        Category category = categoryMapper.toEntity(requestDTO);
        categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.toResponseDTO(category));
    }

    // PUT /api/v1/categories/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id,
                                                              @Valid @RequestBody CategoryRequestDTO requestDTO) {
        Category existingCategory = categoryService.findCategoryById(id);
        categoryMapper.updateEntityFromDto(requestDTO, existingCategory);
        categoryService.updateCategory(existingCategory);
        return ResponseEntity.ok(categoryMapper.toResponseDTO(existingCategory));
    }

    // DELETE /api/v1/categories/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}