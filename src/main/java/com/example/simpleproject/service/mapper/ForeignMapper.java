package com.example.simpleproject.service.mapper;

import com.example.simpleproject.dto.ForeignDebtDto;
import com.example.simpleproject.dto.ProductDto;
import com.example.simpleproject.model.ForeignDebt;
import com.example.simpleproject.model.Product;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class ForeignMapper {

    public abstract ForeignDebtDto toDto(ForeignDebt foreignDebt);

    public abstract ForeignDebt toEntity(ProductDto dto);
}
