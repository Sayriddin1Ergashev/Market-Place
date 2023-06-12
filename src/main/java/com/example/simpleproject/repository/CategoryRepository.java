package com.example.simpleproject.repository;

import com.example.simpleproject.model.Category;
import com.example.simpleproject.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByCategoryIdAndDeletedAtIsNull(Integer categoryId);



    @Query("select c from Category c where "+
    "coalesce(:categoryId,c.categoryId)=c.categoryId AND "+
    "coalesce(:categoryName,c.categoryName)=c.categoryName AND "+
    "coalesce(:productId,c.productId)=c.productId ")



    Page<Category> getBasicSearch(
            @Param("categoryId") Integer integer,
            @Param("categoryName") String categoryName,
            @Param("productId") Integer productId
            , Pageable pageable);
}
