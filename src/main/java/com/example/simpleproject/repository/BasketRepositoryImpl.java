package com.example.simpleproject.repository;


import com.example.simpleproject.model.Basket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
@RequiredArgsConstructor
public class BasketRepositoryImpl {
    private final EntityManager entityManager;

    public Page<Basket> getAdvancedSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt("page");
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt("size");
        }
        StringBuilder builderParam = builderParams(params);

        String strQuery = "select b from Basket b where 1=1";
        String countQuery = "select count (b.basketId)from Basket b where 1=1";

        Query query = entityManager.createQuery(strQuery + builderParam);
        Query queryTwo = entityManager.createQuery(countQuery + builderParam);

        setParams(query, params);
        setParams(queryTwo, params);

        long totalElement = Long.parseLong(queryTwo.getSingleResult().toString());

        return new PageImpl<>(query.getResultList(), PageRequest.of(page, size), totalElement);
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("basketId")) {
            query.setParameter("basketId", params.get("basketId"));
        }
        if (params.containsKey("productId")) {
            query.setParameter("productId", params.get("productId"));
        }
        if (params.containsKey("prodMass")) {
            query.setParameter("prodMass", params.get("prodMass"));
        }
        if (params.containsKey("prodPrice")) {
            query.setParameter("prodPrice", params.get("prodPrice"));
        }
        if (params.containsKey("totalPrice")) {
            query.setParameter("totalPrice", params.get("totalPrice"));
        }

    }

    private StringBuilder builderParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        if (params.containsKey("basketId")) {
            stringBuilder.append("b.basketId : basketId");
        }
        if (params.containsKey("productId")) {
            stringBuilder.append(" AND b.productId : productId");
        }
        if (params.containsKey("prodMass")) {
            stringBuilder.append(" AND b.prodMass : prodMass");
        }
        if (params.containsKey("prodPrice")) {
            stringBuilder.append(" AND b.prodPrice : prodPrice");
        }
        if (params.containsKey("totalPrice")) {
            stringBuilder.append(" AND b.totalPrice : totalPrice");
        }
        return stringBuilder;
    }
}
