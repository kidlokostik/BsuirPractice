package com.example.pizzapp.dto;

import com.example.pizzapp.models.Order;
import com.example.pizzapp.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdersDTO {

    private Long user_id;

    private String address;

    private Double  price;


}
