package com.example.dto;

import lombok.Data;

@Data
public class UsersDTO {
    Long user_id;
    String phone;
    String user_name;
    String password;
    String confirm_password;

}
