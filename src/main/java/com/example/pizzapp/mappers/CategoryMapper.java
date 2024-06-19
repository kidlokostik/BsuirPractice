package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.request.create.CategoryCreateRequest;
import com.example.pizzapp.dto.request.update.CategoryUpdateRequest;
import com.example.pizzapp.dto.response.CategoryResponse;
import com.example.pizzapp.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryCreateRequest toCreateRequest(Category category);
    List<CategoryCreateRequest> toCreateRequest(List<Category> categories);

    @Mapping(target = "product", ignore = true)
    Category createRequestToEntity(CategoryCreateRequest categoryCreateRequest);
    List<Category> createRequestToEntity(List<CategoryCreateRequest> categoryCreateRequests);

    CategoryUpdateRequest toUpdateRequest(Category category);
    List<CategoryUpdateRequest> toUpdateRequest(List<Category> categories);

    @Mapping(target = "product", ignore = true)
    Category updateRequestToEntity(CategoryUpdateRequest categoryUpdateRequest);
    List<Category> updateRequestToEntity(List<CategoryUpdateRequest> categoryUpdateRequests);

    CategoryResponse toResponse(Category category);
    List<CategoryResponse> toResponse(List<Category> categories);

    @Mapping(target = "product", ignore = true)
    Category responseToEntity(CategoryResponse categoryResponse);
    List<Category> responseToEntity(List<CategoryResponse> categoryResponses);

}
