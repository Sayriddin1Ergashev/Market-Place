package com.example.simpleproject.service.validation;

import com.example.simpleproject.dto.BasketDto;
import com.example.simpleproject.dto.ErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BasketValidate {


    public List<ErrorDto> validate(BasketDto dto) {

        return null;
    }
}
