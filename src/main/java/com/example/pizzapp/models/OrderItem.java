package com.example.pizzapp.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderItems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderitems_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
