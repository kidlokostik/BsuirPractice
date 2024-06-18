package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.ProductDto;
import com.example.pizzapp.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "product.category.id", target = "category")
    ProductDto toDto(Product product);

    List<ProductDto> toDto(List<Product> products);
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductDto productDto);
}
