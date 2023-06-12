package com.example.simpleproject.service;

import com.example.simpleproject.dto.CategoryDto;
import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.dto.UsersDto;
import com.example.simpleproject.model.Category;
import com.example.simpleproject.repository.CategoryRepository;
import com.example.simpleproject.repository.CategoryRepositoryImpl;
import com.example.simpleproject.service.mapper.CategoryMapper;
import com.example.simpleproject.service.validation.CategoryValidate;
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
public class CategoryService {

private final CategoryMapper categoryMapper;
private final CategoryRepository categoryRepository;
private final CategoryRepositoryImpl categoryRepositoryImpl;
private final CategoryValidate categoryValidate;
    public ResponseDto<CategoryDto> createCategory(CategoryDto dto) {
        List<ErrorDto>errors=this.categoryValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<CategoryDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        try {
            Category category = categoryMapper.toEntity(dto);
            category.setCreatedAt(LocalDateTime.now());
            categoryRepository.save(category);
            return ResponseDto.<CategoryDto>builder()
                    .success(true)
                    .message("Category successful created!")
                    .data(categoryMapper.toDto(category))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .message("Category while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<CategoryDto> getCategory(Integer categoryId) {
        Optional<Category> optional = categoryRepository.findByCategoryIdAndDeletedAtIsNull(categoryId);
        if (optional.isEmpty()) {
            return ResponseDto.<CategoryDto>builder()
                    .message("Category is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<CategoryDto>builder()
                .success(true)
                .message("OK")
                .data(categoryMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<CategoryDto> updateCategory(Integer categoryId, CategoryDto dto) {
        List<ErrorDto> errors = categoryValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<CategoryDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        Optional<Category> optional = categoryRepository.findByCategoryIdAndDeletedAtIsNull(categoryId);
        if (optional.isEmpty()) {
            return ResponseDto.<CategoryDto>builder()
                    .message("Categoty is not found!")
                    .code(-3)
                    .build();
        }

        try {

            Category category = categoryMapper.toEntity(dto);
            category.setCategoryId(optional.get().getCategoryId());
            category.setCreatedAt(optional.get().getCreatedAt());
            category.setDeletedAt(optional.get().getDeletedAt());
            category.setUpdatedAt(LocalDateTime.now());
            categoryRepository.save(category);

            return ResponseDto.<CategoryDto>builder()
                    .success(true)
                    .message("OK")
                    .data(categoryMapper.toDto(category))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .message("Category while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<CategoryDto> deleteCategory(Integer categoryId) {
        Optional<Category> optional = categoryRepository.findByCategoryIdAndDeletedAtIsNull(categoryId);
        if (optional.isEmpty()) {
            return ResponseDto.<CategoryDto>builder()
                    .message("Category is not found!")
                    .code(-3)
                    .build();
        }
        try {
            Category category = optional.get();
            category.setDeletedAt(LocalDateTime.now());
            categoryRepository.save(category);
            return ResponseDto.<CategoryDto>builder()
                    .message("OK")
                    .success(true)
                    .data(categoryMapper.toDto(category))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .message("Category while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<Page<CategoryDto>> getAdvancedSearch(Map<String, String> params) {
        Page<CategoryDto> category = this.categoryRepositoryImpl.getAdvancedSearch(params)
                .map(this.categoryMapper::toDto);
        if (category.isEmpty()) {
            return ResponseDto.<Page<CategoryDto>>builder()
                    .message("Category is not found")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Page<CategoryDto>>builder()
                .success(true)
                .message("Ok")
                .data(category)
                .build();
    }

    public ResponseDto<Page<CategoryDto>> getBasicSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("params"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }

        Page<CategoryDto>category=this.categoryRepository.getBasicSearch(
                params.get("categotyId")==null?null :Integer.valueOf(params.get("categotyId")),
                params.get("categoryName"),
                params.get("productId")==null ? null:Integer.valueOf(params.get("productId")),
                PageRequest.of(page,size)).map(this.categoryMapper::toDto);

        if (category.isEmpty()) {
            return ResponseDto.<Page<CategoryDto>>builder()
                    .message("Category is not found")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Page<CategoryDto>>builder()
                .success(true)
                .message("OK")
                .code(0)
                .data(category)
                .build();
    }
}
