package com.example.simpleproject.repository;

import com.example.simpleproject.model.Users;
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
public class UsersrepositoryImpl {
    protected  EntityManager entityManager;

    public Page<Users> getAdvancedSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt("page");
        }
        if (params.containsKey("size")) {
            page = Integer.parseInt("size");
        }

        String strQuery = "select u from Users u where 1=1";
        String countQuery = "select count (u.usersId)from Users u where 1=1";
        StringBuilder builderParam = builderParams(params);
        Query query = entityManager.createQuery(strQuery + builderParam);
        Query queryTwo = entityManager.createQuery(countQuery + builderParam);


   setParams(query,params);
   setParams(queryTwo,params);

      long totalElement=Long.parseLong(queryTwo.getSingleResult().toString());
        return new PageImpl<>(query.getResultList(), PageRequest.of(page,size),totalElement);
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("usersId")) {
            query.setParameter("usersId",params.get("usersId"));
        }
        if (params.containsKey("firstName")) {
            query.setParameter("firstName",params.get("firstName"));
        }
        if (params.containsKey("lastName")) {
            query.setParameter("lastName",params.get("lastName"));
        }
        if (params.containsKey("middleName")) {
            query.setParameter("middleName",params.get("middleName"));
        }
        if (params.containsKey("userName")) {
            query.setParameter("userName",params.get("userName"));
        }
        if (params.containsKey("borrowName")) {
            query.setParameter("borrowName",params.get("borrowName"));
        }
        if (params.containsKey("phoneNumber")) {
            query.setParameter("phoneNumber",params.get("phoneNumber"));
        }
        if (params.containsKey("passportSerial")) {
            query.setParameter("passportSerial",params.get("passportSerial"));
        }
        if (params.containsKey("firstAddress")) {
            query.setParameter("firstAddress",params.get("firstAddress"));
        }
        if (params.containsKey("secondAddress")) {
            query.setParameter("secondAddress",params.get("secondAddress"));
        }
        if (params.containsKey("monthlyPrice")) {
            query.setParameter("monthlyPrice",params.get("monthlyPrice"));
        }
    }

    private StringBuilder builderParams(Map<String, String> params) {
StringBuilder stringBuilder=new StringBuilder();
        if (params.containsKey("usersId")) {
            stringBuilder.append("u.usersId: usersId");
        }
        if (params.containsKey("firstName")) {
            stringBuilder.append(" AND u.firstName: firstName");
        }
        if (params.containsKey("lastName")) {
            stringBuilder.append(" AND u.lastName: lastName");
        }
        if (params.containsKey("middleName")) {
            stringBuilder.append(" AND u.middleName: middleName");
        }
        if (params.containsKey("userName")) {
            stringBuilder.append(" AND u.userName: userName");
        }
        if (params.containsKey("borrowName")) {
            stringBuilder.append(" AND u.borrowName: borrowName");
        }
        if (params.containsKey("phoneNumber")) {
            stringBuilder.append(" AND u.phoneNumber: phoneNumber");
        }
        if (params.containsKey("passportSerial")) {
            stringBuilder.append(" AND u.passportSerial: passportSerial");
        }
        if (params.containsKey("firstAddress")) {
            stringBuilder.append(" AND u.firstAddress: firstAddress");
        }
        if (params.containsKey("secondAddress")) {
            stringBuilder.append(" AND u.secondAddress: secondAddress");
        }
        if (params.containsKey("monthlyPrice")) {
            stringBuilder.append(" AND u.monthlyPrice: monthlyPrice");
        }


        return stringBuilder;
    }
}
