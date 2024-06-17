package com.example.pizzapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
public class CategoryDTO {

    @NotNull(message = "Значение не может быть равно 0")
    private Long id;

    @NotBlank(message = "Введите имя пользователя")
    private String name;
}
