package com.example.simpleproject.repository;

import com.example.simpleproject.model.ProductBase;
import com.example.simpleproject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductBaseRepository extends JpaRepository<ProductBase, Integer> {

    Optional<ProductBase> findByProductBaseIdAndDeletedAtIsNull(Integer productBaseId);
}
