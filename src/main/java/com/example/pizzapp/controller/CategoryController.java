package com.example.pizzapp.controller;

import com.example.pizzapp.dto.request.create.CategoryCreateRequest;
import com.example.pizzapp.dto.request.update.CategoryUpdateRequest;
import com.example.pizzapp.dto.response.CategoryResponse;
import com.example.pizzapp.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse createCategory(@RequestBody @Valid CategoryCreateRequest categoryCreateRequest) {
        return categoryService.createCategory(categoryCreateRequest);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryUpdateRequest categoryUpdateRequest) {
        return categoryService.updateCategory(id,categoryUpdateRequest);
    }

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
