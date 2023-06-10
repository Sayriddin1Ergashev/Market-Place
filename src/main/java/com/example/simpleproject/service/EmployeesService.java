package com.example.simpleproject.service;

import com.example.simpleproject.dto.EmployeesDto;
import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.model.Employees;
import com.example.simpleproject.repository.EmployeesRepository;
import com.example.simpleproject.repository.EmployeesRepositoryImpl;
import com.example.simpleproject.service.mapper.EmployeesMapper;
import com.example.simpleproject.service.validation.EmployeesValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeesService {

    private final EmployeesMapper employeesMapper;
    private final EmployeesValidate employeesValidate;
    private final EmployeesRepository employeesRepository;
    private final EmployeesRepositoryImpl employeesRepositoryImpl;

    public ResponseDto<EmployeesDto> createEmployees(EmployeesDto dto) {
        List<ErrorDto> errors = EmployeesValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }
        try {
            Employees employees = employeesMapper.toEntity(dto);
            employees.setCreatedAt(LocalDateTime.now());
            employeesRepository.save(employees);
            return ResponseDto.<EmployeesDto>builder()
                    .success(true)
                    .message("Employees success ful create")
                    .data(employeesMapper.toDto(employees))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Employees while saving error ::" + e.getMessage())
                    .code(-1)
                    .build();
        }

    }

    public ResponseDto<EmployeesDto> getEmployees(Integer employeesId) {
        Optional<Employees> optional = employeesRepository.findByEmployeesIdAndDeletedAtIsNull(employeesId);
        if (optional.isEmpty()) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Employees is not found")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<EmployeesDto>builder()
                .success(true)
                .message("OK")
                .data(employeesMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<EmployeesDto> updateEmployees(Integer employeesId, EmployeesDto dto) {
        List<ErrorDto> errors = employeesValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }
        Optional<Employees> optional = employeesRepository.findByEmployeesIdAndDeletedAtIsNull(employeesId);
        if (optional.isEmpty()) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Employees is not found!")
                    .code(-3)
                    .build();
        }
        try {

            Employees employees = employeesMapper.toEntity(dto);
            employees.setEmployeesId(optional.get().getEmployeesId());
            employees.setUpdatedAt(LocalDateTime.now());
            employeesRepository.save(employees);

            return ResponseDto.<EmployeesDto>builder()
                    .success(true)
                    .message("OK")
                    .data(employeesMapper.toDto(employees))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Employees while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }


    public ResponseDto<EmployeesDto> deleteEmployees(Integer employeesId) {
        Optional<Employees> optional = employeesRepository.findByEmployeesIdAndDeletedAtIsNull(employeesId);
        if (optional.isEmpty()) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Emplpoyees is not found!")
                    .code(-3)
                    .build();
        }
        try {
            Employees employees = optional.get();
            employees.setDeletedAt(LocalDateTime.now());
            employeesRepository.save(employees);

            return ResponseDto.<EmployeesDto>builder()
                    .message("OK")
                    .success(true)
                    .data(employeesMapper.toDto(employees))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Employees while saving error :: %s" + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<Page<EmployeesDto>> getAdvancedSearch(Map<String, String> params) {
Page<EmployeesDto>employees=this.employeesRepositoryImpl.getAdvancedSearch(params)
        .map(this.employeesMapper::toDto);
        if (params.isEmpty()) {
            return ResponseDto.<Page<EmployeesDto>>builder()
                    .message("Employees is not found")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Page<EmployeesDto>>builder()
                .success(true)
                .message("OK")
                .data(employees)
                .build();
    }

    public ResponseDto<Page<EmployeesDto>> getBasicSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }

        Page<EmployeesDto> employees = this.employeesRepository.getBasicSearch(
                params.get("employeesId") == null ? null : Integer.valueOf(params.get("employeesId")),
                 PageRequest.of(page, size)).map(this.employeesMapper::toDto);

        if (employees.isEmpty()) {
            return ResponseDto.<Page<EmployeesDto>>builder()
                    .message("Employees is not found")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Page<EmployeesDto>>builder()
                .success(true)
                .message("OK")
                .code(0)
                .data(employees)
                .build();
    }
}
