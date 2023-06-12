package com.example.simpleproject.controller;


import com.example.simpleproject.dto.ProductDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(value = "product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping(value = ("/create"))
    public ResponseDto<ProductDto>createProduct(@RequestBody ProductDto dto){
        return productService.createProduct(dto);
    }

    @GetMapping(value = ("/get/{id}"))
    public ResponseDto<ProductDto>getProduct(@PathVariable("id")Integer productId){
        return productService.getProduct(productId);
    }

@PutMapping(value = ("/update/{id}"))
    public ResponseDto<ProductDto>updateProduct(@PathVariable("id") Integer productId,
                                                @RequestBody ProductDto dto){
        return productService.updateProduct(productId,dto);
}

@DeleteMapping(value = ("/delete/{id}"))
    public ResponseDto<ProductDto>deleteProduct(@PathVariable("id") Integer productId){
        return productService.deleteProduct(productId);
}

    @GetMapping(value = ("/get-advanced-search"))
    public ResponseDto<Page<ProductDto>> getAdvancedSearch(@RequestParam Map<String,String> params) {
        return productService.getAdvancedSearch(params);
    }


}
