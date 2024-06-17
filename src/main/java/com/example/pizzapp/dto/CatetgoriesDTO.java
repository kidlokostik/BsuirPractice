package com.example.pizzapp.dto;

import com.example.pizzapp.models.Order;
import com.example.pizzapp.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriesDTO {

    private Long id;

    private String category_name;

}
