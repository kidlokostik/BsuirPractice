package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.CategoryDto;
import com.example.pizzapp.models.Category;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    List<CategoryDto> toDto(List<Category> categories);

    Category toEntity(CategoryDto categoryDto);
}
