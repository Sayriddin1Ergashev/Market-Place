package com.example.simpleproject.service.validation;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ProductDto;
import com.example.simpleproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductValidate {

private final ProductRepository productRepository;

    public static List<ErrorDto> validate(ProductDto dto) {

        return null;
    }
}
