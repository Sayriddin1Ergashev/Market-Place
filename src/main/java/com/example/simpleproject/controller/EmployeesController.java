package com.example.simpleproject.controller;

import com.example.simpleproject.dto.EmployeesDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.dto.UsersDto;
import com.example.simpleproject.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = ("employees"))
@RequiredArgsConstructor
public class EmployeesController {

private final EmployeesService employeesService;

@PostMapping(value = ("/create"))
    public ResponseDto<EmployeesDto>createEmployees(@RequestBody EmployeesDto dto){
    return employeesService.createEmployees(dto);
}

@GetMapping(value = ("/get/{id}"))
    public ResponseDto<EmployeesDto>getEmployees(@PathVariable("id") Integer employeesId){
    return employeesService.getEmployees(employeesId);
}

@PutMapping(value = ("/update/{id}"))
    public ResponseDto<EmployeesDto>updateEmployees(@PathVariable("id") Integer employeesId,
                                                    @RequestBody EmployeesDto dto){
    return employeesService.updateEmployees(employeesId,dto);
}

@DeleteMapping(value = ("/delete/{id}"))
    public ResponseDto<EmployeesDto>deleteEmployees(@PathVariable("id") Integer employeesId){
    return employeesService.deleteEmployees(employeesId);
}

    @GetMapping(value = ("/get-advanced-search"))
    public ResponseDto<Page<EmployeesDto>> getAdvancedSearch(@RequestParam Map<String,String> params) {
        return employeesService.getAdvancedSearch(params);
    }

    @GetMapping(value = ("/get-basic-search"))
    public ResponseDto<Page<EmployeesDto>> getBasicSearch(@RequestParam Map<String, String> params) {
        return employeesService.getBasicSearch(params);
    }
}
