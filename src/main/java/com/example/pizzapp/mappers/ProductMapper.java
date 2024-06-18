package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.ProductDto;
import com.example.pizzapp.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(source = "product.category.id", target = "categoryId")
    ProductDto toDto(Product product);

    List<ProductDto> toDto(List<Product> products);
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductDto productDto);
}
