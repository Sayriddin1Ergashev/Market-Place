package com.example.simpleproject.service.mapper;

import com.example.simpleproject.dto.CategoryDto;
import com.example.simpleproject.dto.ProductDto;
import com.example.simpleproject.model.Category;
import com.example.simpleproject.model.Product;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class CategoryMapper {

    public abstract CategoryDto toDto(Category category);

    public abstract Category toEntity(CategoryDto dto);
}
