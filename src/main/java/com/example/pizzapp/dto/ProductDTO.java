package com.example.pizzapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    @NotNull(message = "Значение не может быть равно null")
    private Long id;

    @NotBlank(message = "Название товара не должно быть пустым")
    private String name;

    @NotNull(message = "Значение не может быть равно null")
    private Long category;
}
