package com.example.simpleproject.service.mapper;

import com.example.simpleproject.dto.ProductBaseDto;
import com.example.simpleproject.model.ProductBase;
import com.example.simpleproject.service.ImageService;
import com.example.simpleproject.service.ProductService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class ProductBaseMapper {


   /* @Lazy
    @Autowired
    protected ProductMapper productMapper;
    @Lazy
    @Autowired
    protected ProductService productService;*/
    public abstract ProductBaseDto toDto(ProductBase productBase);

    public abstract ProductBase toEntity(ProductBaseDto dto);
}
