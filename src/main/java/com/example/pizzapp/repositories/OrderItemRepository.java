package com.example.pizzapp.repositories;

import com.example.pizzapp.models.OrderItem;
import com.example.pizzapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findById (Long order_id);

}
