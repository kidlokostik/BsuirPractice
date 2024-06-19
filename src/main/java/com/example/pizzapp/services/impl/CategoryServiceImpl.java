package com.example.pizzapp.services.impl;

import com.example.pizzapp.dto.request.create.CategoryCreateRequest;
import com.example.pizzapp.dto.request.update.CategoryUpdateRequest;
import com.example.pizzapp.dto.response.CategoryResponse;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.mappers.CategoryMapper;
import com.example.pizzapp.models.Category;
import com.example.pizzapp.repositories.CategoryRepository;
import com.example.pizzapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryCreateRequest createCategoryRequest) {
        Category category = categoryMapper.createRequestToEntity(createCategoryRequest);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryUpdateRequest updateCategoryRequest) {
        Category newestCategory = findCategoryByIdOrThrow(id);
        categoryMapper.toUpdateRequest(newestCategory);

        Category updateCategory = categoryRepository.save(newestCategory);
        return categoryMapper.toResponse(updateCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = findCategoryByIdOrThrow(id);
        return categoryMapper.toResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
       return categoryRepository.findAll().stream()
               .map(categoryMapper::toResponse)
               .toList();
    }

    private Category findCategoryByIdOrThrow(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
    }

}