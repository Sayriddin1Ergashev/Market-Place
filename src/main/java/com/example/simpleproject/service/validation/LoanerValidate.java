package com.example.simpleproject.service.validation;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.LoanerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoanerValidate {


    public List<ErrorDto> validate(LoanerDto dto) {

        return null;
    }
}
