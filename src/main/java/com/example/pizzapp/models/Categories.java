package com.example.pizzapp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(nullable = false)
    private String category_name;

    @OneToMany(mappedBy = "category")
    private List<Products> products = new ArrayList<>();

}
