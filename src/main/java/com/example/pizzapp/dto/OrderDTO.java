package com.example.pizzapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;
@Data
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    private Long user;

    private String address;

    @NotNull(message = "Цена не должна быть null")
    @Positive(message = "Цена должна быть положительной")
    private BigDecimal price;

}
