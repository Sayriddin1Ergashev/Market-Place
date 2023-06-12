package com.example.simpleproject.controller;

import com.example.simpleproject.dto.LoanerDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.service.LoanerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = ("loaner"))
@RequiredArgsConstructor
public class LoanerController {

    private final LoanerService loanerService;

    @PostMapping(value = "/create")
    public ResponseDto<LoanerDto> createLoaner(@RequestBody LoanerDto dto) {
        return loanerService.createLoaner(dto);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseDto<LoanerDto> getLoaner(@PathVariable("id") Integer loanerId) {
        return loanerService.getLoaner(loanerId);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseDto<LoanerDto> updateLoaner(@PathVariable("id") Integer loanerId,
                                               @RequestBody LoanerDto dto){
        return loanerService.updateLoaner(loanerId,dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseDto<LoanerDto>deleteLoaner(@PathVariable("id") Integer loanerId){
        return loanerService.deleteLoaner(loanerId);
    }


    @GetMapping(value = ("/get-advanced-search"))
    public ResponseDto<Page<LoanerDto>> getAdvancedSearch(@RequestParam Map<String,String> params) {
        return loanerService.getAdvancedSearch(params);
    }


}
