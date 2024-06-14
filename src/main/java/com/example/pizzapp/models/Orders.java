package com.example.pizzapp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private double price;

    @OneToMany(mappedBy = "order")
    private List<OrderItems> orderItems = new ArrayList<>();
}
