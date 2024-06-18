package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.ProductDTO;
import com.example.pizzapp.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "product.category.id", target = "category")
    ProductDTO toDTO(Product product);

    List<ProductDTO> toDTO(List<Product> products);
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductDTO productDTO);
}
