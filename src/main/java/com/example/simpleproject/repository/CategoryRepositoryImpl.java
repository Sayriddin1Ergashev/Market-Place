package com.example.simpleproject.repository;

import com.example.simpleproject.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl {


    public Page<Category> getAdvancedSearch(Map<String, String> params) {

        return null;
    }
}
