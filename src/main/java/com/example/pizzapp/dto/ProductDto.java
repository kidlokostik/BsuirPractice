package com.example.pizzapp.dto;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    @NotNull(message = "Значение не может быть равно null", groups = OnUpdate.class)
    private Long id;

    @NotBlank(message = "Название товара не должно быть пустым", groups = {OnUpdate.class, OnCreate.class})
    private String name;

    @NotNull(message = "Значение не может быть равно null", groups = {OnUpdate.class, OnCreate.class})
    private Long category;
}
