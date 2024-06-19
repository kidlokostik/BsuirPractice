package com.example.pizzapp.services.impl;

import com.example.pizzapp.dto.CategoryDto;
import com.example.pizzapp.mappers.CategoryMapper;
import com.example.pizzapp.models.Category;
import com.example.pizzapp.repositories.CategoryRepository;
import com.example.pizzapp.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category){
       return categoryRepository.createCategory(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        return categoryRepository.updateCategory(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
//        return categoryRepository.findAll().stream()
//                .map(categoryMapper::toDto)
//                .toList();
        return null;
    }
}
