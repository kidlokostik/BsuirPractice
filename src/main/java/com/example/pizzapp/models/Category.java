package com.example.pizzapp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "category_id", nullable = false)
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> product = new ArrayList<>();

}
