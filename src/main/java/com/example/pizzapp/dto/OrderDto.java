package com.example.pizzapp.dto;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class OrderDto {

    @NotNull(message = "Значение не может быть равно null", groups = OnUpdate.class)
    private Long id;

    @NotBlank(message = "Имя пользователя не должно быть пустым", groups = {OnUpdate.class, OnCreate.class})
    private Long user;

    @NotBlank(message = "Адресс пользователя не должен быть пустым", groups = {OnUpdate.class, OnCreate.class})
    private String address;

    @NotNull(message = "Цена не должна быть null", groups = {OnUpdate.class, OnCreate.class})
    @Positive(message = "Цена должна быть положительной", groups = {OnUpdate.class, OnCreate.class})
    private BigDecimal price;

}
