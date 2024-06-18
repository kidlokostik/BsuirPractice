package com.example.pizzapp.repositories;

import com.example.pizzapp.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Orders> findAllByUserId(Long id);

    Optional<Order> findById(Long id);

}
