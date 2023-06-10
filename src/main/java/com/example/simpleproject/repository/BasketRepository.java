package com.example.simpleproject.repository;

import com.example.simpleproject.model.Basket;
import com.example.simpleproject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {

   // Optional<Users> findByBasketIddAndDeletedAtIsNull(Integer userId);
}
