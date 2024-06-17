package com.example.pizzapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String phone;

    private String user_name;

    private String password;

    private String confirm_password;
}
