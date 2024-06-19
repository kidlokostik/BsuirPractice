package com.example.pizzapp.services;

import com.example.pizzapp.dto.CategoryDto;
import com.example.pizzapp.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category createCategoryRequest);
    Category updateCategory(Long id, Category updateCategoryRequest);
    void deleteCategory(Long id);
    Category getCategoryById(Long id);
    List<CategoryDto> getAllCategories();
}
