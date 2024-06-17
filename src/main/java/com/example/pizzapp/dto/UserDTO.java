package com.example.pizzapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "Значение не может быть равно 0")
    private Long id;

    @NotBlank(message = "Введите номер телефона")
    @Pattern(regexp = "\\+?[0-9]{7,15}", message = "Телефон должен быть действительным номером")
    private String phone;

    @NotBlank(message = "Введите имя пользователя")
    private String name;

    @NotBlank(message = "Введите пароль")
    @Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
    private String password;

    @NotBlank(message = "Введите подтверждение пароля")
    @Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
    private String confirm_password;
}
