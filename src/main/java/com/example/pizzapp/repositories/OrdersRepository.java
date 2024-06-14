package com.example.pizzapp.repositories;

import com.example.pizzapp.models.Orders;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    //Для создания и обновления используется метод save

    @Override
    List<Orders> findAll();

    List<Orders> findByUserId(Long user_id);

    @Override
    Optional<Orders> findById(Long order_id);

}
