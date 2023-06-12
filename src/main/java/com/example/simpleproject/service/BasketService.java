package com.example.simpleproject.service;

import com.example.simpleproject.dto.BasketDto;
import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.model.Basket;
import com.example.simpleproject.repository.BasketRepository;
import com.example.simpleproject.repository.BasketRepositoryImpl;
import com.example.simpleproject.service.mapper.BasketMapper;
import com.example.simpleproject.service.validation.BasketValidate;
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
public class BasketService {


    private final BasketMapper basketMapper;
    private final BasketRepository basketRepository;
    private final BasketValidate basketValidate;
    private final BasketRepositoryImpl basketRepositoryImpl;

    public ResponseDto<BasketDto> createBasket(BasketDto dto) {
        List<ErrorDto> errors = this.basketValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        try {
            Basket basket = basketMapper.toEntity(dto);
            basket.setCreatedAt(LocalDateTime.now());
            basketRepository.save(basket);
            return ResponseDto.<BasketDto>builder()
                    .success(true)
                    .message("Basket successful created!")
                    .data(basketMapper.toDto(basket))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<BasketDto> getBasket(Integer basketId) {
        Optional<Basket> optional = basketRepository.findByBasketIdAndDeletedAtIsNull(basketId);
        if (optional.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<BasketDto>builder()
                .success(true)
                .message("OK")
                .data(basketMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<BasketDto> updateBasket(Integer basketId, BasketDto dto) {
        List<ErrorDto> errors = basketValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        Optional<Basket> optional = basketRepository.findByBasketIdAndDeletedAtIsNull(basketId);
        if (optional.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("Baasket is not found!")
                    .code(-3)
                    .build();
        }

        try {

            Basket basket = basketMapper.toEntity(dto);
            basket.setBasketId(optional.get().getBasketId());
            basket.setUpdatedAt(LocalDateTime.now());
            basketRepository.save(basket);

            return ResponseDto.<BasketDto>builder()
                    .success(true)
                    .message("OK")
                    .data(basketMapper.toDto(basket))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<BasketDto> deleteBasket(Integer basketId) {
        Optional<Basket> optional = basketRepository.findByBasketIdAndDeletedAtIsNull(basketId);
        if (optional.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket is not found!")
                    .code(-3)
                    .build();
        }
        try {
            Basket basket = optional.get();
            basket.setDeletedAt(LocalDateTime.now());
            basketRepository.save(basket);
            return ResponseDto.<BasketDto>builder()
                    .message("OK")
                    .success(true)
                    .data(basketMapper.toDto(basket))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<Page<BasketDto>> getAdvancedSearch(Map<String, String> params) {
        Page<BasketDto> basket = this.basketRepositoryImpl.getAdvancedSearch(params)
                .map(this.basketMapper::toDto);
        if (basket.isEmpty()) {
            return ResponseDto.<Page<BasketDto>>builder()
                    .message("Basket is not found")
                    .code(-1)
                    .build();
        }

        return ResponseDto.<Page<BasketDto>>builder()
                .success(true)
                .message("OK")
                .data(basket)
                .build();
    }

    public ResponseDto<Page<BasketDto>> getBasicSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }

        Page<BasketDto> basket = this.basketRepository.getBasicSearch(
                params.get("basketId") == null ? null : Integer.valueOf(params.get("basketId")),
                params.get("productId") == null ? null : Integer.valueOf(params.get("productId")),
                params.get("prodMass") == null ? null : Double.parseDouble(params.get("prodMass")),
                params.get("prodPrice") == null ? null : Double.parseDouble(params.get("prodPrice")),
                params.get("totalPrice") == null ? null : Double.parseDouble(params.get("totalPrice")),
                PageRequest.of(page, size)
                ).map(this.basketMapper::toDto);
        if (basket.isEmpty()) {
            return ResponseDto.<Page<BasketDto>>builder()
                    .message("Basket is not found")
                    .code(-1)
                    .build();
        }
    return ResponseDto.<Page<BasketDto>>builder()
            .success(true)
            .message("OK")
            .code(0)
            .data(basket)
            .build();
    }

}
