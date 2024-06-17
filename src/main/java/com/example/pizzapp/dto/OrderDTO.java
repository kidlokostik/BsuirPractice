package com.example.pizzapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;
@Data
@NoArgsConstructor
public class OrderDTO {
    @NotNull(message = "Значение не может быть равно null")
    private Long id;
    NotBlank(message = "Имя пользователя не должно быть пустым")
    private Long user;
    NotBlank(message = "Адресс пользователя не должен быть пустым")
    private String address;

    @NotNull(message = "Цена не должна быть null")
    @Positive(message = "Цена должна быть положительной")
    private BigDecimal price;

}
