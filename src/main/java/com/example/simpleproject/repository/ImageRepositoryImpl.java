package com.example.simpleproject.repository;

import com.example.simpleproject.model.Image;
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
public class ImageRepositoryImpl {

    private final EntityManager entityManager;

    public Page<Image> getAdvancedSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }


        String strQuery = "select i from Image i where 1=1";
        String countQuery = "select count (i.imageId)from Image i where 1=1";

        StringBuilder builderParam = builderParams(params);
        Query query= entityManager.createQuery(strQuery+builderParam);
        Query queryTwo=entityManager.createQuery(countQuery+builderParam);

        setParams(query,params);
        setParams(queryTwo,params);

        long  toatalElement=Long.parseLong(queryTwo.getSingleResult().toString());
        return new PageImpl<>(query.getResultList(), PageRequest.of(page,size),toatalElement);
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("imageId")) {
            query.setParameter("imageId",params.get("imageId"));
        }
        if (params.containsKey("usersId")) {
            query.setParameter("usersId",params.get("usersId"));
        }
        if (params.containsKey("productId")) {
            query.setParameter("productId",params.get("productId"));
        }
        if (params.containsKey("path")) {
            query.setParameter("path",params.get("path"));
        }
        if (params.containsKey("type")) {
            query.setParameter("type",params.get("type"));
        }
        if (params.containsKey("size")) {
            query.setParameter("size",params.get("size"));
        }
        if (params.containsKey("token")) {
            query.setParameter("token",params.get("token"));
        }



    }

    private StringBuilder builderParams(Map<String, String> params) {
        StringBuilder stringBuilder=new StringBuilder();
        if (params.containsKey("imageId")) {
            stringBuilder.append("i.imageId : imageId");
        }
        if (params.containsKey("usersId")) {
            stringBuilder.append(" AND i.usersId : usersId");
        }
        if (params.containsKey("productId")) {
            stringBuilder.append(" AND i.productId : productId");
        }
        if (params.containsKey("path")) {
            stringBuilder.append(" AND i.path : path");
        }
        if (params.containsKey("type")) {
            stringBuilder.append(" AND i.type : type");
        }
        if (params.containsKey("size")) {
            stringBuilder.append(" AND i.size : size");
        }
        if (params.containsKey("token")) {
            stringBuilder.append(" AND i.token : token");
        }

        return stringBuilder;
    }
}
