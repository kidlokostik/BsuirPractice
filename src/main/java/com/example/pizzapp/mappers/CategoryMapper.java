package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.UserDTO;



@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    List<CategoryDTO> toDTO(List<Category> categorys);

    User toEntity(CategoryDTO categoryDTO);
}
