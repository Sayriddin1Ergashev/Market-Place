package com.example.simpleproject.service.mapper;

import com.example.simpleproject.dto.EmployeesDto;
import com.example.simpleproject.model.Employees;
import com.example.simpleproject.service.EmployeesService;
import com.example.simpleproject.service.UsersService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collectors.class})
public abstract class EmployeesMapper {





    @Lazy
    @Autowired
    protected UsersService usersService;
    protected UsersMapper usersMapper;

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Employees toEntity(EmployeesDto dto);


    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    public abstract EmployeesDto toDto(Employees employees);


}
