package com.example.pizzapp.dto;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
    @NotNull(message = "Значение не может быть равно 0", groups = OnUpdate.class)
    private Long id;

    @NotBlank(message = "Введите имя пользователя", groups = {OnUpdate.class, OnCreate.class})
    private String name;
}
