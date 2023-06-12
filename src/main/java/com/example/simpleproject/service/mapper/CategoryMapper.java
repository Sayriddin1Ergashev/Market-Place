package com.example.simpleproject.service.mapper;

import com.example.simpleproject.dto.CategoryDto;
import com.example.simpleproject.model.Category;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class CategoryMapper {

    public abstract CategoryDto toDto(Category category);

    public abstract Category toEntity(CategoryDto dto);

    public Page<CategoryDto> getBasicSearch(Integer integer, String categoryName, Integer integer1, PageRequest of) {

        return null;
    }
}
