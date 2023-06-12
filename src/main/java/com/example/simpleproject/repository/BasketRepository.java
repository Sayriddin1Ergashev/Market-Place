package com.example.simpleproject.repository;

import com.example.simpleproject.model.Basket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {

    Optional<Basket> findByBasketIdAndDeletedAtIsNull(Integer basketId);

    @Query("select b from Basket  b where "+
    "coalesce(:basketId,b.basketId)=b.basketId AND "+
    "coalesce(:productId,b.productId)=b.productId AND "+
    "coalesce(:prodMass,b.prodMass)=b.prodMass AND "+
    "coalesce(:prodPrice,b.prodPrice)=b.prodPrice AND "+
    "coalesce(:totalPrice,b.totalPrice)=b.totalPrice "
    )
    Page<Basket> getBasicSearch(

          @Param("basketId") Integer basketId,
           @Param("productId") Integer productId,
            @Param("prodMass") Double prodMass,
            @Param("prodPrice") Double prodPrice,
            @Param("totalPrice") Double totalPrice,
            Pageable pageable);

}
