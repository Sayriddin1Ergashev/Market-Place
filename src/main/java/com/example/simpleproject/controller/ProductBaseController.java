package com.example.simpleproject.controller;


import com.example.simpleproject.dto.ProductBaseDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.service.ProductBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = ("productbase"))
@RequiredArgsConstructor
public class ProductBaseController {


    private final ProductBaseService productBaseService;



    @PostMapping(value = "/create")
    public ResponseDto<ProductBaseDto> createProductBase(@RequestBody ProductBaseDto dto) {
        return productBaseService.createProductBase(dto);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseDto<ProductBaseDto> getProductBase(@PathVariable("id") Integer productBaseId) {
        return productBaseService.getProductBase(productBaseId);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseDto<ProductBaseDto> updateProductBase(@PathVariable("id") Integer productBaseId,
                                               @RequestBody ProductBaseDto dto){
        return productBaseService.updateProductBase(productBaseId,dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseDto<ProductBaseDto>deleteProductBase(@PathVariable("id") Integer productBaseId){
        return productBaseService.deleteProductBase(productBaseId);
    }


    @GetMapping(value = ("/get-advanced-search"))
    public ResponseDto<Page<ProductBaseDto>> getAdvancedSearch(@RequestParam Map<String,String> params) {
        return productBaseService.getAdvancedSearch(params);
    }




}
