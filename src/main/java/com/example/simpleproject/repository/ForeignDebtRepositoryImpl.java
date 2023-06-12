package com.example.simpleproject.repository;

import com.example.simpleproject.model.ForeignDebt;
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
public class ForeignDebtRepositoryImpl {
    protected EntityManager entityManager;

    public Page<ForeignDebt> getAdvancedSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }

        String strQuery = "select f from ForeignDebt f where 1=1";
        String countQuery = "select count (f.foreignDebtId)from ForeignDebt f where 1=1";
        StringBuilder builderParam = builderParams(params);
        Query query = entityManager.createQuery(strQuery + builderParam);
        Query queryTwo = entityManager.createQuery(countQuery + builderParam);


        setParams(query,params);
        setParams(queryTwo,params);

        long totalElement=Long.parseLong(queryTwo.getSingleResult().toString());
        return new PageImpl<>(query.getResultList(), PageRequest.of(page,size),totalElement);
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("foreignDebtId")) {
            query.setParameter("foreignDebtId",params.get("foreignDebtId"));
        }
        if (params.containsKey("companyName")) {
            query.setParameter("companyName",params.get("companyName"));
        }
        if (params.containsKey("fullName")) {
            query.setParameter("fullName",params.get("fullName"));
        }
        if (params.containsKey("firstPhoneNumber")) {
            query.setParameter("firstPhoneNumber",params.get("firstPhoneNumber"));
        }
        if (params.containsKey("secondPhoneNumber")) {
            query.setParameter("secondPhoneNumber",params.get("secondPhoneNumber"));
        }
        if (params.containsKey("productId")) {
            query.setParameter("productId",params.get("productId"));
        }
        if (params.containsKey("status")) {
            query.setParameter("status",params.get("status"));
        }
    }

    private StringBuilder builderParams(Map<String, String> params) {
        StringBuilder stringBuilder=new StringBuilder();
        if (params.containsKey("foreignDebtId")){
            stringBuilder.append("f.foreignDebtId : foreignDebtId");
        }
        if (params.containsKey("companyName")){
            stringBuilder.append(" AND f.companyName : companyName");
        }
        if (params.containsKey("fullName")){
            stringBuilder.append(" AND f.fullName : fullName");
        }
        if (params.containsKey("firstPhoneNumber")){
            stringBuilder.append(" AND f.firstPhoneNumber : firstPhoneNumber");
        }
        if (params.containsKey("secondPhoneNumber")){
            stringBuilder.append(" AND f.secondPhoneNumber : secondPhoneNumber");
        }
        if (params.containsKey("productId")){
            stringBuilder.append(" AND f.productId : productId");
        }
        if (params.containsKey("status")){
            stringBuilder.append(" AND f.status : status");
        }
        return stringBuilder;
    }
}
