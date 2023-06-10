package com.example.simpleproject.service.mapper;

import com.example.simpleproject.dto.ProductBaseDto;
import com.example.simpleproject.dto.ProductDto;
import com.example.simpleproject.model.Product;
import com.example.simpleproject.model.ProductBase;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class ProductBaseMapper {

    public abstract ProductBaseDto toDto(ProductBase productBase);

    public abstract ProductBase toEntity(ProductDto dto);
}
