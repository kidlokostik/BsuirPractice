package com.example.pizzapp.repositories;

import com.example.pizzapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    User createUser(User user);

    User updateUser(User user);
}