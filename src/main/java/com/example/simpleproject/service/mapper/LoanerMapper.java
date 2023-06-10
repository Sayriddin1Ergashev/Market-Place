package com.example.simpleproject.service.mapper;

import com.example.simpleproject.dto.LoanerDto;
import com.example.simpleproject.dto.ProductDto;
import com.example.simpleproject.model.Loaner;
import com.example.simpleproject.model.Product;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class LoanerMapper {

    public abstract LoanerDto toDto(Loaner loaner);

    public abstract Loaner toEntity(LoanerDto dto);
}
