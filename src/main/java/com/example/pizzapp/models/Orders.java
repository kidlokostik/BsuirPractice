package com.example.pizzapp.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;

    private String address;
    private double price;
}
