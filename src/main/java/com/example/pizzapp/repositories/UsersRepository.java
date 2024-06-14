package com.example.pizzapp.repositories;

import com.example.pizzapp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @Override
    Optional<Users> findById (Long user_id);

    @Override
    List<Users> findAll();

    @Override
    void deleteAll();

    @Override
    void deleteById(Long user_id);


}
