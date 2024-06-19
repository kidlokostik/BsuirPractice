package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.request.create.ProductCreateRequest;
import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.request.update.ProductUpdateRequest;
import com.example.pizzapp.dto.response.ProductResponse;
import com.example.pizzapp.models.Product;
import com.example.pizzapp.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(source = "product.category.id", target = "categoryId")
    ProductCreateRequest toCreateRequest(Product product);
    List<ProductCreateRequest> toCreateRequest(List<Product> products);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Product createRequestToEntity(ProductCreateRequest productCreateRequest);
    List<Product> createRequestToEntity(List<ProductCreateRequest> productCreateRequests);

    @Mapping(source = "product.category.id", target = "categoryId")
    ProductUpdateRequest toUpdateRequest(Product product);
    List<ProductUpdateRequest> toUpdateRequest(List<Product> products);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Product updateRequestToEntity(ProductUpdateRequest productUpdateRequest);
    List<Product> updateRequestToEntity(List<ProductUpdateRequest> productUpdateRequests);

    @Mapping(source = "product.category.id", target = "categoryId")
    ProductResponse toResponse(Product product);
    List<ProductResponse> toResponse(List<Product> products);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Product responseToEntity(ProductResponse productResponse);
    List<Product> responseToEntity(List<ProductResponse> productResponses);
}
