package com.example.simpleproject.service.validation;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.UsersDto;
import com.example.simpleproject.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class UsersValidate {
    private final UsersRepository usersRepository;

    public List<ErrorDto> validate(UsersDto dto) {

        return null;
    }
}