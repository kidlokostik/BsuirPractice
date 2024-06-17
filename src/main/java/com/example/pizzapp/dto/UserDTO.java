package com.example.pizzapp.dto;

import com.example.pizzapp.models.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String phone;

    private String user_name;

    private String password;

    private String confirm_password;

    private List<Order> orders = new ArrayList<>();
}
