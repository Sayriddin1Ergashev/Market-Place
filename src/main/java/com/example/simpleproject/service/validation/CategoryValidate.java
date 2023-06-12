package com.example.simpleproject.service.validation;

import com.example.simpleproject.dto.CategoryDto;
import com.example.simpleproject.dto.ErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryValidate {


    public List<ErrorDto> validate(CategoryDto dto) {

        return null;
    }
}
