package com.example.pizzapp.mapper;

import com.example.pizzapp.dto.request.create.ProductCreateRequest;
import com.example.pizzapp.dto.request.update.ProductUpdateRequest;
import com.example.pizzapp.dto.response.ProductResponse;
import com.example.pizzapp.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Product createRequestToEntity(ProductCreateRequest productCreateRequest);

    Product updateRequestToEntity(Long id, ProductUpdateRequest productUpdateRequest);

    @Mapping(source = "product.category.id", target = "categoryId")
    ProductResponse toResponse(Product product);
}
