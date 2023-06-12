package com.example.simpleproject.service.validation;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ForeignDebtDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ForeignDebtValidate {


    public List<ErrorDto> validate(ForeignDebtDto dto) {


        return null;
    }
}
