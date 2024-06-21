package com.example.pizzapp.mapper;

import com.example.pizzapp.dto.request.create.CategoryCreateRequest;
import com.example.pizzapp.dto.request.update.CategoryUpdateRequest;
import com.example.pizzapp.dto.response.CategoryResponse;
import com.example.pizzapp.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    @Mapping(target = "product", ignore = true)
    Category createRequestToEntity(CategoryCreateRequest categoryCreateRequest);

    @Mapping(target = "product", ignore = true)
    Category updateRequestToEntity(Long id, CategoryUpdateRequest categoryUpdateRequest);

    CategoryResponse toResponse(Category category);
}
