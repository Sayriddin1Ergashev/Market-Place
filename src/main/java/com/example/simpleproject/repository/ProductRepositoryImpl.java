package com.example.simpleproject.repository;

import com.example.simpleproject.model.Product;
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
public class ProductRepositoryImpl {

    private final EntityManager entityManager;

    public Page<Product> getAdvancedSearch(Map<String, String> params) {

        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }
        String strQuery = "select p from Product p where 1=1";
        String countQuery = "select count (p.productId)from Product p where 1=1";

        StringBuilder builderParam = builderParams(params);

        Query query= entityManager.createQuery(strQuery+builderParam);
        Query queryTwo=entityManager.createQuery(countQuery+builderParam);

        setParams(query,params);
        setParams(queryTwo,params);

        long totalElement=Long.parseLong(queryTwo.getSingleResult().toString());
        return new PageImpl<>(query.getResultList(), PageRequest.of(page,size),totalElement);

      }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("productId")){
            query.setParameter("productId",params.get("productId"));
        }
        if (params.containsKey("prodName")){
            query.setParameter("prodName",params.get("prodName"));
        }
        if (params.containsKey("receivedPrice")){
            query.setParameter("receivedPrice",params.get("receivedPrice"));
        }
        if (params.containsKey("sellingPrice")){
            query.setParameter("sellingPrice",params.get("sellingPrice"));
        }
        if (params.containsKey("prodMass")){
            query.setParameter("prodMass",params.get("prodMass"));
        }
        if (params.containsKey("amount")){
            query.setParameter("amount",params.get("amount"));
        }
        if (params.containsKey("productBaseId")){
            query.setParameter("productBaseId",params.get("productBaseId"));
        }
        if (params.containsKey("imageId")){
            query.setParameter("imageId",params.get("imageId"));
        }
    }

    private StringBuilder builderParams(Map<String, String> params) {
        StringBuilder stringBuilder=new StringBuilder();
        if (params.containsKey("productId")) {
            stringBuilder.append("p.productId : productId");
        }
        if (params.containsKey("prodName")) {
            stringBuilder.append(" AND p.prodName : prodName");
        }
        if (params.containsKey("receivedPrice")) {
            stringBuilder.append(" AND p.receivedPrice : receivedPrice");
        }
        if (params.containsKey("sellingPrice")) {
            stringBuilder.append(" AND p.sellingPrice : sellingPrice");
        }
        if (params.containsKey("prodMass")) {
            stringBuilder.append(" AND p.prodMass : prodMass");
        }
        if (params.containsKey("amount")) {
            stringBuilder.append(" AND p.amount : amount");
        }
        if (params.containsKey("productBaseId")) {
            stringBuilder.append(" AND p.productBaseId : productBaseId");
        }
        if (params.containsKey("imageId")) {
            stringBuilder.append(" AND p.imageId : imageId");
        }
        return stringBuilder;
    }
}
