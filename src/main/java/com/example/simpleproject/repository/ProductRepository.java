package com.example.simpleproject.repository;

import com.example.simpleproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer > {

    Optional<Product> findByProductIdAndDeletedAtIsNull(Integer productId);
}
