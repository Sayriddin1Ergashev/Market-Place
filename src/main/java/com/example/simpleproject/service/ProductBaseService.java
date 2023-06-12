package com.example.simpleproject.service;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ProductBaseDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.model.ProductBase;
import com.example.simpleproject.repository.ProductBaseRepository;
import com.example.simpleproject.repository.ProductBaseRepositoryImpl;
import com.example.simpleproject.service.mapper.ProductBaseMapper;
import com.example.simpleproject.service.validation.ProductBaseValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductBaseService {
    private final ProductBaseMapper productBaseMapper;
    private final ProductBaseRepository productBaseRepository;
    private final ProductBaseValidate productBaseValidate;
    private final ProductBaseRepositoryImpl productBaseRepositoryImpl;

    public ResponseDto<ProductBaseDto> createProductBase(ProductBaseDto dto) {
        List<ErrorDto> errors = this.productBaseValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        try {
            ProductBase productBase = productBaseMapper.toEntity(dto);
            productBase.setCreatedAt(LocalDateTime.now());
            productBaseRepository.save(productBase);
            return ResponseDto.<ProductBaseDto>builder()
                    .success(true)
                    .message("ProductBase successful created!")
                    .data(productBaseMapper.toDto(productBase))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("ProductBase while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<ProductBaseDto> getProductBase(Integer productBaseId) {
        Optional<ProductBase> optional = productBaseRepository.findByProductBaseIdAndDeletedAtIsNull(productBaseId);
        if (optional.isEmpty()) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("ProductBase is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<ProductBaseDto>builder()
                .success(true)
                .message("OK")
                .data(productBaseMapper.toDto(optional.get()))
                .build();
    }


    public ResponseDto<ProductBaseDto> updateProductBase(Integer productBaseId, ProductBaseDto dto) {
        List<ErrorDto> errors = productBaseValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        Optional<ProductBase> optional = productBaseRepository.findByProductBaseIdAndDeletedAtIsNull(productBaseId);
        if (optional.isEmpty()) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("ProductBase is not found!")
                    .code(-3)
                    .build();
        }

        try {

            ProductBase productBase = productBaseMapper.toEntity(dto);
            productBase.setProductBaseId(optional.get().getProductBaseId());
            productBase.setUpdatedAt(LocalDateTime.now());
            productBaseRepository.save(productBase);

            return ResponseDto.<ProductBaseDto>builder()
                    .success(true)
                    .message("OK")
                    .data(productBaseMapper.toDto(productBase))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("ProductBase while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<ProductBaseDto> deleteProductBase(Integer productBaseId) {
        Optional<ProductBase> optional = productBaseRepository.findByProductBaseIdAndDeletedAtIsNull(productBaseId);
        if (optional.isEmpty()) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("ProductBase is not found!")
                    .code(-3)
                    .build();
        }
        try {
            ProductBase productBase = optional.get();
            productBase.setDeletedAt(LocalDateTime.now());
            productBaseRepository.save(productBase);
            return ResponseDto.<ProductBaseDto>builder()
                    .message("OK")
                    .success(true)
                    .data(productBaseMapper.toDto(productBase))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<ProductBaseDto>builder()
                    .message("ProductBase while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<Page<ProductBaseDto>> getAdvancedSearch(Map<String, String> params) {
        Page<ProductBaseDto> productBase = this.productBaseRepositoryImpl.getAdvancedSearch(params)
                .map(this.productBaseMapper::toDto);
        if (productBase.isEmpty()) {
            return ResponseDto.<Page<ProductBaseDto>>builder()
                    .message("ProductBase is not found")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Page<ProductBaseDto>>builder()
                .success(true)
                .message("Ok")
                .data(productBase)
                .build();
    }


}
/*
 public ResponseDto<Page<UsersDto>> getAdvancedSearch(Map<String, String> params) {
        Page<UsersDto> users = this.usersRepositoryImpl.getAdvancedSearch(params)
                .map(this.usersMapper::toDto);
        if (users.isEmpty()) {
            return ResponseDto.<Page<UsersDto>>builder()
                    .message("Users is not found")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Page<UsersDto>>builder()
                .success(true)
                .message("Ok")
                .data(users)
                .build();
    }

    public ResponseDto<Page<UsersDto>> getBasicSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("params"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }
        Page<UsersDto> users = this.usersRepository.getBasicSearch(
                params.get("usersId") == null ? null : Integer.valueOf(params.get("usersId")),
                params.get("employeesId") == null ? null : Integer.valueOf(params.get("employeesId")),
                params.get("firstName"),
                params.get("lastName"),
                params.get("middleName"),
                params.get("userName"),
                params.get("borrowName"),
                params.get("phoneNumber"),
                params.get("passportSerial"),
                params.get("firstAddress"),
                params.get("secondAddress"),
                params.get("monthlyPrice") == null ? null : Integer.valueOf(params.get("monthlyPrice")),
                PageRequest.of(page, size)).map(this.usersMapper::toDto);

        if (users.isEmpty()) {
            return ResponseDto.<Page<UsersDto>>builder()
                    .message("Users is not found")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Page<UsersDto>>builder()
                .success(true)
                .message("OK")
                .code(0)
                .data(users)
                .build();
    }

*/
