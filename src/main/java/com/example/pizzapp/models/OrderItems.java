package com.example.pizzapp.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product_id;

}
