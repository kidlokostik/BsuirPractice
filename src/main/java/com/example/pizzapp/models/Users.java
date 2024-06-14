package com.example.pizzapp.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String phone;

    private String user_name;
    private String password;
    private String confirm_password;

}
