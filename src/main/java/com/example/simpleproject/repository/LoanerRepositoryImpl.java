package com.example.simpleproject.repository;

import com.example.simpleproject.model.Loaner;
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
public class LoanerRepositoryImpl {

    private final EntityManager entityManager;
    public Page<Loaner> getAdvancedSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }

        String strQuery = "select l from Loaner l where 1=1";
        String countQuery = "select count (l.loanerId)from Loaner l where 1=1";

        StringBuilder builderParam = builderParams(params);

        Query query= entityManager.createQuery(strQuery+builderParam);
        Query queryTwo=entityManager.createQuery(countQuery+builderParam);


        setParams(query,params);
        setParams(queryTwo,params);

        long toatalElement=Long.parseLong(queryTwo.getSingleResult().toString());
        return new PageImpl<>(query.getResultList(),PageRequest.of(page,size),toatalElement);

    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("loanerId")){
            query.setParameter("loanerId",params.get("loanerId"));
        }
        if (params.containsKey("totalPrice")) {
            query.setParameter("totalPrice",params.get("totalPrice"));
        }
        if (params.containsKey("status")) {
            query.setParameter("status",params.get("status"));
        }
    }

    private StringBuilder builderParams(Map<String, String> params) {
        StringBuilder stringBuilder=new StringBuilder();
        if (params.containsKey("loanerId")) {
            stringBuilder.append("l.loanerId : loanerId");
        }
        if (params.containsKey("totalPrice")) {
            stringBuilder.append(" AND l.totalPrice : totalPrice");
        }
        if (params.containsKey("status")) {
            stringBuilder.append(" AND l.status : status");
        }
        return stringBuilder;
    }
}
