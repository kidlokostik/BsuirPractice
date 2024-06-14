package com.example.pizzapp.repositories;


import com.example.pizzapp.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

 //Для создания и обновления используется метод save
 @Override
 Optional<Products> findById(Long product_id);

 Optional<Products> findByName(String product_name);

 List<Products> findByCategoryId(Long category_id);

 @Override
 void deleteAll();

 @Override
 void deleteById(Long product_id);
}
