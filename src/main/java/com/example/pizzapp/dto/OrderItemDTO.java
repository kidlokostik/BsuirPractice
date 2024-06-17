package com.example.pizzapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDTO {

    private Long id;

    private Long product;

    private Long order;
}
