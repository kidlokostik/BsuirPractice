package com.example.pizzapp.dto;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
public class CategoryDTO {

    @NotNull(message = "Значение не может быть равно 0", groups = OnUpdate.class)
    private Long id;

    @NotBlank(message = "Введите имя пользователя", groups = {OnUpdate.class, OnCreate.class})
    private String name;
}
