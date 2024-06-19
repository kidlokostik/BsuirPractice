package com.example.pizzapp.repositories;

import com.example.pizzapp.dto.request.create.CategoryCreateRequest;
import com.example.pizzapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}