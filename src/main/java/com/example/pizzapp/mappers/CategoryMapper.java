package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.UserDTO;
import com.example.pizzapp.models.User;
import org.mapstruct.Mapper;

import java.util.List;
import com.example.pizzapp.models.Order;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    List<CategoryDTO> toDTO(List<Category> categorys);

    User toEntity(CategoryDTO categoryDTO);
}
