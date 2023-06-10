package com.example.simpleproject.service.mapper;


import com.example.simpleproject.dto.UsersDto;
import com.example.simpleproject.model.Users;

import com.example.simpleproject.service.ImageService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring", imports = {Collectors.class})
public abstract class UsersMapper {

    @Lazy
    @Autowired
    protected ImageMapper imageMapper;
    @Lazy
    @Autowired
    protected ImageService imageService;

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Users toEntity(UsersDto dto);
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
//    @Mapping(target = "image", expression = "java(users.getImage()." +
//            "map(imageMapper::toDtoNotUsersId).collect(Collectors.toSet()))")

    public abstract UsersDto toDto(Users users);

    @Mapping(target = "image",ignore = true)
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
    public abstract UsersDto toDtoByNotImage(Users users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Users users, UsersDto dto);







}















