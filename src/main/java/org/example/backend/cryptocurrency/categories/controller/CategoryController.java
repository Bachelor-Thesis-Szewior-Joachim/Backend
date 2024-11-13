package org.example.backend.cryptocurrency.categories.controller;

import org.example.backend.cryptocurrency.categories.entity.CategoryDto;
import org.example.backend.cryptocurrency.categories.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryInfo(@PathVariable String categoryId) {
        Optional<CategoryDto> optionalCategoryDto = categoryService.findByCategoryId(categoryId);

        return optionalCategoryDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        Optional<List<CategoryDto>> optionalCategoryDto = categoryService.findAllCategories();

        return optionalCategoryDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
