package com.example.pizzapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDTO {
    @NotNull(message = "Значение id не может быть равно null")
    private Long id;
    @NotNull(message = "Значение product не может быть равно null")
    private Long product;
    @NotNull(message = "Значение order не может быть равно null")
    private Long order;
}
