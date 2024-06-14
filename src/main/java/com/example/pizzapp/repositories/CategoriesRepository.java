package com.example.pizzapp.repositories;

import com.example.pizzapp.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    //Для создания и обновления используется метод save

    @Override
    void deleteById(Long category_id);

    @Override
    void deleteAll();

    @Override
    List<Categories> findAll();
}
