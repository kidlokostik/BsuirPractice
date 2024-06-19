package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.request.create.CategoryCreateRequest;
import com.example.pizzapp.dto.request.update.CategoryUpdateRequest;
import com.example.pizzapp.dto.response.CategoryResponse;
import com.example.pizzapp.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryCreateRequest toCreateRequest(Category category);
    List<CategoryCreateRequest> toCreateRequest(List<Category> categories);

    Category createRequestToEntity(CategoryCreateRequest categoryCreateRequest);
    List<Category> createRequestToEntity(List<CategoryCreateRequest> categoryCreateRequests);

    CategoryUpdateRequest toUpdateRequest(Category category);
    List<CategoryUpdateRequest> toUpdateRequest(List<Category> categories);

    Category updateRequestToEntity(CategoryUpdateRequest categoryUpdateRequest);
    List<Category> updateRequestToEntity(List<CategoryUpdateRequest> categoryUpdateRequests);

    CategoryResponse toResponse(Category category);
    List<CategoryResponse> toResponse(List<Category> categories);

    Category responseToEntity(CategoryResponse categoryResponse);
    List<Category> responseToEntity(List<CategoryResponse> categoryResponses);

}
