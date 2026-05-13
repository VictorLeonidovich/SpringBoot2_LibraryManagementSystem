package com.kvl.library.controller;

import com.kvl.library.entity.Category;
import com.kvl.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
    public static final String CATEGORIES = "categories";
    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String findAllCategories(Model model) {
        model.addAttribute(CATEGORIES, categoryService.findAllCategories());
        return CATEGORIES;
    }

    @GetMapping("/remove-category/{id}")
    public String removeCategory(@PathVariable Long id, Model model) {
        categoryService.deleteCategory(id);
        model.addAttribute(CATEGORIES, categoryService.findAllCategories());
        return CATEGORIES;
    }

    @GetMapping("/update-category/{id}")
    public String updateCategory(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.findCategoryById(id));
        return "update-category";
    }

    @PostMapping("/save-category/{id}")
    public String saveCategory(@PathVariable Long id, Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "update-category";
        }
        categoryService.updateCategory(category);
        model.addAttribute(CATEGORIES, categoryService.findAllCategories());
        return "redirect:/categories";
    }

    @GetMapping("/add-category")
    public String addCategory(Category category) {
        return "add-category";
    }

    @PostMapping("/save-category")
    public String saveCategory(Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-category";
        }
        categoryService.createCategory(category);
        model.addAttribute(CATEGORIES, categoryService.findAllCategories());
        return "redirect:/categories";
    }
}
