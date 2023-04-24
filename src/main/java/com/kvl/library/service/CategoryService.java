package com.kvl.library.service;

import com.kvl.library.entity.Book;
import com.kvl.library.entity.Category;
import com.kvl.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(final Long id) {
        return findById(id);
    }

    private Category findById(final Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category in not found"));
    }

    public void createCategory(final Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(final Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(final Long id) {
        final Category category = findById(id);
        categoryRepository.deleteById(category.getId());
    }
}
