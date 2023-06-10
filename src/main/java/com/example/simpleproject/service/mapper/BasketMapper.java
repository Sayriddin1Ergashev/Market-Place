package com.example.simpleproject.service.mapper;

import com.example.simpleproject.dto.BasketDto;
import com.example.simpleproject.dto.ProductDto;
import com.example.simpleproject.model.Basket;
import com.example.simpleproject.model.Product;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class BasketMapper {

    public abstract BasketDto toDto(Basket basket);

    public abstract Basket toEntity(BasketDto dto);
}
