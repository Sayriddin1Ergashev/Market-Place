package com.example.simpleproject.repository;

import com.example.simpleproject.model.Category;
import com.example.simpleproject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

  //  Optional<Users> findByCategoryIdAndDeletedAtIsNull(Integer userId);
}
