package com.example.pizzapp.mapper;

import com.example.pizzapp.dto.request.create.CategoryCreateRequest;
import com.example.pizzapp.dto.response.CategoryResponse;
import com.example.pizzapp.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    @Mapping(target = "product", ignore = true)
    Category createRequestToEntity(CategoryCreateRequest categoryCreateRequest);

    CategoryResponse toResponse(Category category);
}
