package com.example.simpleproject.repository;

import com.example.simpleproject.model.Reports;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReportsRepositoryImpl {

    private final EntityManager entityManager;
    public Page<Reports> getAdvancedSearch(Map<String, String> params) {

        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }
        String strQuery = "select r from Reports r where 1=1";
        String countQuery = "select count (r.reportsId)from Reports r where 1=1";

        StringBuilder builderParam = builderParams(params);

        Query query= entityManager.createQuery(strQuery+builderParam);
        Query queryTwo=entityManager.createQuery(countQuery+builderParam);

        setParams(query,params);
        setParams(queryTwo,params);


        long totalElement=Long.parseLong(queryTwo.getSingleResult().toString());
        return new PageImpl<>(query.getResultList(), PageRequest.of(page,size),totalElement);
    }

    private void setParams(Query query, Map<String, String> params) {

        if (params.containsKey("reportsId")){
            query.setParameter("reportsId",params.get("reportsId"));
        }
        if (params.containsKey("categoryId")){
            query.setParameter("categoryId",params.get("categoryId"));
        }
        if (params.containsKey("prodName")){
            query.setParameter("prodName",params.get("prodName"));
        }
        if (params.containsKey("prodPresent")){
            query.setParameter("prodPresent",params.get("prodPresent"));
        }
    }

    private StringBuilder builderParams(Map<String, String> params) {
        StringBuilder stringBuilder=new StringBuilder();
        if (params.containsKey("reportsId")) {
            stringBuilder.append("r.reportsId : reportsId");
        }
        if (params.containsKey("categoryId")) {
            stringBuilder.append(" AND r.categoryId : categoryId");
        }
        if (params.containsKey("prodName")) {
            stringBuilder.append(" AND r.prodName : prodName");
        }
        if (params.containsKey("prodPresent")) {
            stringBuilder.append(" AND r.prodPresent : prodPresent");
        }
        return stringBuilder;
    }
}
