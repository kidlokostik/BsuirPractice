package com.example.pizzapp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String confirm_password;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

}
