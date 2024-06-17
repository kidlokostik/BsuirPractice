package com.example.pizzapp.dto;

import com.example.pizzapp.models.Order;
import com.example.pizzapp.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDTO {

    private Long id;

    private Product product;

    private Order order;
}
