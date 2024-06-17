package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.CategoryDTO;
import com.example.pizzapp.models.Category;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    List<CategoryDTO> toDTO(List<Category> categories);

    Category toEntity(CategoryDTO categoryDTO);
}
