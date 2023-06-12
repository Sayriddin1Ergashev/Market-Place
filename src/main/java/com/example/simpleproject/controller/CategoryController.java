package com.example.simpleproject.controller;


import com.example.simpleproject.dto.CategoryDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = ("category"))
@RequiredArgsConstructor
public class CategoryController {

private final CategoryService categoryService;


    @PostMapping(value = "/create")
    public ResponseDto<CategoryDto> createCategory(@RequestBody CategoryDto dto) {
        return categoryService.createCategory(dto);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseDto<CategoryDto> getCategory(@PathVariable("id") Integer categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseDto<CategoryDto> updateCategory(@PathVariable("id") Integer categoryId,
                                               @RequestBody CategoryDto dto){
        return categoryService.updateCategory(categoryId,dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseDto<CategoryDto>deleteCategory(@PathVariable("id") Integer categoryId){
        return categoryService.deleteCategory(categoryId);
    }

    @GetMapping(value = ("/get-advanced-search"))
    public ResponseDto<Page<CategoryDto>> getAdvancedSearch(@RequestParam Map<String,String> params) {
        return categoryService.getAdvancedSearch(params);
    }

    @GetMapping(value = ("/get-basic-search"))
    public ResponseDto<Page<CategoryDto>> getBasicSearch(@RequestParam Map<String, String> params) {
        return categoryService.getBasicSearch(params);
    }
}
