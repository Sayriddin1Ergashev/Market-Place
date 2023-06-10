package com.example.simpleproject.repository;

import com.example.simpleproject.model.Employees;
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
public class EmployeesRepositoryImpl {
    protected final EntityManager entityManager;


    public Page<Employees>getAdvancedSearch(Map<String, String> params) {
 int size =10, page =0;
        if (params.containsKey("page")) {
            page=Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size=Integer.parseInt(params.get("size"));
        }

        String strQuery=" select e from Employees e where 1=1";
        String countQuery="select count (e.employeesId) from Employees e where 1=1";
        StringBuilder builderParams = builderParams(params);
        Query query= entityManager.createQuery(strQuery+builderParams);
        Query queryTwo =entityManager.createQuery(countQuery+builderParams);


        setParams(query,params);
        setParams(queryTwo,params);
        long totalElement=Long.parseLong(queryTwo.getSingleResult().toString());

        return new PageImpl<>(query.getResultList(), PageRequest.of(page,size),totalElement);
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("employeesId")) {
            query.setParameter("employeesId",params.get("employeesId"));
        }
    }

    private StringBuilder builderParams(Map<String, String> params) {
        StringBuilder stringBuilder=new StringBuilder();
        if (params.containsKey("employeesId")) {
            stringBuilder.append("e.employeesId:employeesId");
        }
        return stringBuilder;
    }
}
