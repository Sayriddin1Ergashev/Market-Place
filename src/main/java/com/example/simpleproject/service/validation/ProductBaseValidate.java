package com.example.simpleproject.service.validation;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ProductBaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductBaseValidate {


    public List<ErrorDto> validate(ProductBaseDto dto) {

        return null;
    }
}
