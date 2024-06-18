package com.example.pizzapp.dto;

import com.example.pizzapp.dto.validation.OnCreate;
import com.example.pizzapp.dto.validation.OnUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;


public record UserDto(@NotNull(message = "Значение не может быть равно null", groups = OnUpdate.class)
                      Long id,
                      @NotBlank(message = "Номер телефона не должен быть пустым", groups = {OnUpdate.class, OnCreate.class})
                      @Pattern(regexp = "\\+?[0-9]{7,15}", message = "Телефон должен быть действительным номером", groups = {OnUpdate.class, OnCreate.class})
                      String phone,
                      @NotBlank(message = "Имя пользователя не должно быть пустым", groups = {OnUpdate.class, OnCreate.class})
                      String name,
                      @NotBlank(message = "Пароль не должен быть пустым", groups = {OnUpdate.class, OnCreate.class})
                      @Size(min = 6, message = "Пароль должен содержать не менее 6 символов", groups = {OnUpdate.class, OnCreate.class})
                      String password,
                      @NotBlank(message = "Подтверждение пароля не должно быть пустым", groups = {OnUpdate.class, OnCreate.class})
                      @Size(min = 6, message = "Пароль должен содержать не менее 6 символов", groups = {OnUpdate.class, OnCreate.class})
                      String confirmPassword) {}
