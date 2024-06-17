package com.example.pizzapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    private Long user;

    private String address;

    private BigDecimal price;

}
