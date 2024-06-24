package com.example.pizzapp.service;

import com.example.pizzapp.dto.request.create.CategoryCreateRequest;
import com.example.pizzapp.dto.request.update.CategoryUpdateRequest;
import com.example.pizzapp.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryCreateRequest createCategoryRequest);

    CategoryResponse updateCategory(Long id, CategoryUpdateRequest categoryUpdateRequest);

    void deleteCategory(Long id);

    CategoryResponse getCategoryById(Long id);

    List<CategoryResponse> getAllCategories();

    boolean uniqueCategoryCheck(CategoryCreateRequest createCategoryRequest);

    boolean uniqueCategoryCheck(CategoryUpdateRequest updateCategoryRequest);
}
