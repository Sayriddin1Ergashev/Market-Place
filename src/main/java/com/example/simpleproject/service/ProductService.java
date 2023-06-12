package com.example.simpleproject.service;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ProductDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.dto.UsersDto;
import com.example.simpleproject.model.Product;
import com.example.simpleproject.repository.ProductRepository;
import com.example.simpleproject.repository.ProductRepositoryImpl;
import com.example.simpleproject.service.mapper.ProductMapper;
import com.example.simpleproject.service.validation.ProductValidate;
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
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductValidate productValidate;
    private final ProductRepositoryImpl productRepositoryImpl;

    public ResponseDto<ProductDto> createProduct(ProductDto dto) {
        List<ErrorDto> errors = ProductValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ProductDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }
        try {
            Product product = productMapper.toEntity(dto);
            product.setCreatedAt(LocalDateTime.now());
            productRepository.save(product);
            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.productMapper.toDto(product))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .message("Product while saving error ::" + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<ProductDto> getProduct(Integer productId) {
        Optional<Product> optional = productRepository.findByProductIdAndDeletedAtIsNull(productId);
        if (optional.isEmpty()) {
            return ResponseDto.<ProductDto>builder()
                    .message("Product is not found")
                    .code(-1)
                    .data(null)
                    .build();
        }
        return ResponseDto.<ProductDto>builder()
                .success(true)
                .message("OK")
                .data(productMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<ProductDto> updateProduct(Integer productId, ProductDto dto) {
List<ErrorDto>errors=productValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ProductDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        Optional<Product>optional=productRepository.findByProductIdAndDeletedAtIsNull(productId);
        if (optional.isEmpty()){
            return ResponseDto.<ProductDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .build();
        }try {
            Product product=productMapper.toEntity(dto);
            product.setUpdatedAt(LocalDateTime.now());
            productRepository.save(product);
            return ResponseDto.<ProductDto>builder()
                    .message("Ok")
                    .success(true)
                    .data(this.productMapper.toDto(optional.get()))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ProductDto>builder()
                    .message("Product while saving error %s"+e.getMessage())
                    .code(-3)
                    .build();
        }

    }

    public ResponseDto<ProductDto> deleteProduct(Integer productId) {
Optional<Product>optional=productRepository.findByProductIdAndDeletedAtIsNull(productId);
        if (optional.isEmpty()) {
            return ResponseDto.<ProductDto>builder()
                    .message("Product is not found")
                    .code(-1)
                    .data(productMapper.toDto(optional.get()))
                    .build();
        }
        try {
            Product product= optional.get();
            product.setProductId(optional.get().getProductId());
            product.setDeletedAt(LocalDateTime.now());
            productRepository.save(product);
            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .message("OK")
                    .data(productMapper.toDto(optional.get()))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ProductDto>builder()
                    .message("Product while saving error %s"+e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<Page<ProductDto>> getAdvancedSearch(Map<String, String> params) {
        Page<ProductDto> product = this.productRepositoryImpl.getAdvancedSearch(params)
                .map(this.productMapper::toDto);
        if (product.isEmpty()) {
            return ResponseDto.<Page<ProductDto>>builder()
                    .message("Product is not found")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Page<ProductDto>>builder()
                .success(true)
                .message("Ok")
                .data(product)
                .build();
    }


}
