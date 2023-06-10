package com.example.simpleproject.service.validation;

import com.example.simpleproject.dto.EmployeesDto;
import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeesValidate {

private final EmployeesRepository employeesRepository;


    public static List<ErrorDto> validate(EmployeesDto dto) {

        return null;
    }
}
