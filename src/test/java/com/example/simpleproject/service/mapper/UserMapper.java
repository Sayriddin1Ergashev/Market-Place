package com.example.simpleproject.service.mapper;

import com.example.simpleproject.dto.ImageDto;
import com.example.simpleproject.dto.UsersDto;
import com.example.simpleproject.model.Users;
import com.example.simpleproject.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.Mapping;

import java.util.Set;
import java.util.stream.Collectors;
//@Mapper(componentModel = "spring", imports = {Collectors.class})
public abstract class UserMapper {


       /* @Lazy
        @Autowired
        protected ImageService imageService;

        @Lazy
        @Autowired
        protected ImageMapper imageMapper;

        @Mapping(target = "createdAt", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        @Mapping(target = "deletedAt", ignore = true)
        public abstract Users toEntity(UsersDto dto);

        @Mapping(target = "images", expression = "java(users.getImage().stream()." +
                "map(imageMapper::toDtoNotUserId).collect(Collectors.toSet()))")
        public abstract UsersDto toDto(Users users);

        public void viewMethod(){
            UsersDto dto = new UsersDto();
            Users users = new Users();
            Set<ImageDto> cardDtoSet;
            cardDtoSet = users.getImage().stream().
                    map(imageMapper::toDtoNotUsersId).collect(Collectors.toSet());
            dto.setImage(imageDtoSet);
        }
*/
}
