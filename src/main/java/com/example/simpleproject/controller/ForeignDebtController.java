package com.example.simpleproject.controller;

import com.example.simpleproject.dto.BasketDto;
import com.example.simpleproject.dto.ForeignDebtDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.service.ForeignDebtService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = ("foreigndebt"))
@RequiredArgsConstructor
public class ForeignDebtController {

    private final ForeignDebtService foreignDebtService;


    @PostMapping(value = "/create")
    public ResponseDto<ForeignDebtDto> createForeignDebt(@RequestBody ForeignDebtDto dto) {
        return foreignDebtService.createForeignDebt(dto);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseDto<ForeignDebtDto> getForeignDebt(@PathVariable("id") Integer ForeignDebtId) {
        return foreignDebtService.getForeignDebt(ForeignDebtId);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseDto<ForeignDebtDto> updateForeignDebt(@PathVariable("id") Integer ForeignDebtId,
                                                         @RequestBody ForeignDebtDto dto) {
        return foreignDebtService.updateForeignDebt(ForeignDebtId, dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseDto<ForeignDebtDto> deleteForeignDebt(@PathVariable("id") Integer ForeignDebtId) {
        return foreignDebtService.deleteForeignDebt(ForeignDebtId);
    }

    @GetMapping(value = ("/get-advanced-search"))
    public ResponseDto<Page<ForeignDebtDto>> getAdvancedSearch(@RequestParam Map<String, String> params) {
        return foreignDebtService.getAdvancedSearch(params);
    }

   /* @GetMapping(value = ("/get-basic-search"))
    public ResponseDto<Page<ForeignDebtDto>> getBasicSearch(@RequestParam Map<String, String> params) {
        return foreignDebtService.getBasicSearch(params);
    }*/
}
