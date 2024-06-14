package com.example.pizzapp.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    private String category_name;

}
