package com.example.simpleproject.service.validation;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ReportsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReportsValidate {


    public List<ErrorDto> validate(ReportsDto dto) {

        return null;
    }
}
