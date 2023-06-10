package com.example.simpleproject.service.mapper;

import com.example.simpleproject.dto.ProductDto;
import com.example.simpleproject.dto.ReportsDto;
import com.example.simpleproject.model.Product;
import com.example.simpleproject.model.Reports;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class ReportsMapper {

    public abstract ReportsDto toDto(Reports reports);

    public abstract Reports toEntity(ReportsDto dto);
}
