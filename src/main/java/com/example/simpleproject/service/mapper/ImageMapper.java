package com.example.simpleproject.service.mapper;

import com.example.simpleproject.dto.ImageDto;
import com.example.simpleproject.model.Image;
import com.example.simpleproject.service.UsersService;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = {Collectors.class})
public abstract class ImageMapper {


 @Mapping(target = "createdAt", ignore = true)
 @Mapping(target = "updatedAt", ignore = true)
 @Mapping(target = "deletedAt", ignore = true)
 @Mapping(target = "imageId", ignore = true)
 public abstract Image toEntity(ImageDto imageDto);

 @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
 @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
 @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
 public abstract ImageDto toDto(Image image);

 @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd")
 @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd")
 @Mapping(target = "deletedAt", dateFormat = "yyyy-MM-dd")
 @Mapping(target = "imageId", ignore = true)
 public abstract ImageDto toDtoNotUsersId(Image images);

 @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
 public abstract void update(@MappingTarget Image image, ImageDto imageDto);
}
