package com.example.pizzapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "Значение не может быть равно null")
    private Long id;

    @NotBlank(message = "Номер телефона не должен быть пустым")
    @Pattern(regexp = "\\+?[0-9]{7,15}", message = "Телефон должен быть действительным номером")
    private String phone;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String name;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
    private String password;

    @NotBlank(message = "Подтверждение пароля не должно быть пустым")
    @Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
    private String confirm_password;
}
