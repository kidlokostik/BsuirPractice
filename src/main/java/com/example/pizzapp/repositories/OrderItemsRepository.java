package com.example.pizzapp.repositories;

import com.example.pizzapp.models.OrderItems;
import com.example.pizzapp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    @Override
    Optional<OrderItems> findById (Long order_id);

    @Override
    List<OrderItems> findAll();

    @Override
    void deleteAll();

}
