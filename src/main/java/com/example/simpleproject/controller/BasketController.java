package com.example.simpleproject.controller;

import com.example.simpleproject.dto.BasketDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = ("basket"))
@RequiredArgsConstructor
public class BasketController {


    private final BasketService basketService;

    @PostMapping(value = "/create")
    public ResponseDto<BasketDto> createBasket(@RequestBody BasketDto dto) {
        return basketService.createBasket(dto);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseDto<BasketDto> getBasket(@PathVariable("id") Integer basketId) {
        return basketService.getBasket(basketId);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseDto<BasketDto> updateBasket(@PathVariable("id") Integer basketId,
                                               @RequestBody BasketDto dto){
        return basketService.updateBasket(basketId,dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseDto<BasketDto>deleteBasket(@PathVariable("id") Integer basketId){
        return basketService.deleteBasket(basketId);
    }

    @GetMapping(value = ("/get-advanced-search"))
    public ResponseDto<Page<BasketDto>> getAdvancedSearch(@RequestParam Map<String,String> params) {
        return basketService.getAdvancedSearch(params);
    }

    @GetMapping(value = ("/get-basic-search"))
    public ResponseDto<Page<BasketDto>> getBasicSearch(@RequestParam Map<String, String> params) {
        return basketService.getBasicSearch(params);
    }
}
