package com.example.pizzapp.dto;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {
    @NotNull(message = "Значение id не может быть равно null", groups = OnUpdate.class)
    private Long id;
    @NotNull(message = "Значение product не может быть равно null", groups = {OnUpdate.class, OnCreate.class})
    private Long product;
    @NotNull(message = "Значение order не может быть равно null", groups = {OnUpdate.class, OnCreate.class})
    private Long order;
}
