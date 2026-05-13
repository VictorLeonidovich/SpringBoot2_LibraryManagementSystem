package com.kvl.library.service;

import com.kvl.library.entity.Category;
import com.kvl.library.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        log.info("Fetching all categories from the database");
        return categoryRepository.findAll();
    }

    public Category findCategoryById(final Long id) {
        Category category = findById(id);
        log.info("Fetched category '{}' by id '{}' from the database", category, id);
        return category;
    }

    private Category findById(final Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category in not found"));
    }

    public void createCategory(final Category category) {
        log.info("Saving category '{}' to the database", category);
        categoryRepository.save(category);
    }

    public void updateCategory(final Category category) {
        log.info("Updating category '{}' in the database", category);
        categoryRepository.save(category);
    }

    public void deleteCategory(final Long id) {
        final Category category = findById(id);
        log.info("Deleting category '{}' by id '{}' from the database", category, id);
        categoryRepository.deleteById(category.getId());
    }
}
