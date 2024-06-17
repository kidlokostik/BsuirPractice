package com.example.pizzapp.dto;

import com.example.pizzapp.models.Category;
import com.example.pizzapp.models.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private Category category;

    private List<OrderItem> orderItems = new ArrayList<>();
}
