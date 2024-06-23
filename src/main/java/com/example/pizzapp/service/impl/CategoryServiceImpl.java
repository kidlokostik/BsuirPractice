package com.example.pizzapp.service.impl;

import com.example.pizzapp.dto.request.create.CategoryCreateRequest;
import com.example.pizzapp.dto.request.update.CategoryUpdateRequest;
import com.example.pizzapp.dto.response.CategoryResponse;
import com.example.pizzapp.exception.DuplicateFoundException;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.mapper.CategoryMapper;
import com.example.pizzapp.model.Category;
import com.example.pizzapp.repository.CategoryRepository;
import com.example.pizzapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import static com.example.pizzapp.util.ErrorMessages.DUPLICATE_FOUND;
import static com.example.pizzapp.util.ErrorMessages.NOT_FOUND_MESSAGE;
import java.util.List;



@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryCreateRequest createCategoryRequest) {
        if (uniqueCategoryCheck(createCategoryRequest)) {
            throw new DuplicateKeyException(String.format(DUPLICATE_FOUND, "category", createCategoryRequest.name()));
        }
        Category category = categoryMapper.createRequestToEntity(createCategoryRequest);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryUpdateRequest updateCategoryRequest) {
        Category category = findCategoryByIdOrThrow(id);
        if (uniqueCategoryCheck(updateCategoryRequest)) {
            throw new DuplicateFoundException(String.format(DUPLICATE_FOUND, "category", updateCategoryRequest.name()));
        }
        categoryMapper.updateCategoryFromUpdateRequest(updateCategoryRequest,category);
        return categoryMapper.toResponse(categoryRepository.save(category));
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

    @Override
    public boolean uniqueCategoryCheck(CategoryCreateRequest createCategoryRequest) {
        return categoryRepository.existsByName(createCategoryRequest.name());
    }

    @Override
    public boolean uniqueCategoryCheck(CategoryUpdateRequest updateCategoryRequest) {
        return categoryRepository.existsByName(updateCategoryRequest.name());
    }

    private Category findCategoryByIdOrThrow(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "Category", id))
                );
    }
}
