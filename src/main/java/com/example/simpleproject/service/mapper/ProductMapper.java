package com.example.simpleproject.service.mapper;

import com.example.simpleproject.dto.ProductDto;
import com.example.simpleproject.model.Product;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class ProductMapper {




    public abstract ProductDto toDto(Product product);

    public abstract Product toEntity(ProductDto dto);
}
