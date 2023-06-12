package com.example.simpleproject.service;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ForeignDebtDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.model.ForeignDebt;
import com.example.simpleproject.repository.ForeignDebtRepository;
import com.example.simpleproject.repository.ForeignDebtRepositoryImpl;
import com.example.simpleproject.service.mapper.ForeignDebtMapper;
import com.example.simpleproject.service.validation.ForeignDebtValidate;
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
public class ForeignDebtService {

    private final ForeignDebtMapper foreignDebtMapper;
    private final ForeignDebtRepository foreignDebtRepository;
    private final ForeignDebtValidate foreignDebtValidate;
    private final ForeignDebtRepositoryImpl foreignDebtRepositoryImpl;

    public ResponseDto<ForeignDebtDto> createForeignDebt(ForeignDebtDto dto) {
        List<ErrorDto> errors = this.foreignDebtValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        try {
            ForeignDebt foreignDebt = foreignDebtMapper.toEntity(dto);
            foreignDebt.setCreatedAt(LocalDateTime.now());
            foreignDebtRepository.save(foreignDebt);
            return ResponseDto.<ForeignDebtDto>builder()
                    .success(true)
                    .message("ForeignDebt successful created!")
                    .data(foreignDebtMapper.toDto(foreignDebt))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("ForeignDebt while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }

    }

    public ResponseDto<ForeignDebtDto> getForeignDebt(Integer foreignDebtId) {
        Optional<ForeignDebt> optional = foreignDebtRepository.findByForeignDebtIdAndDeletedAtIsNull(foreignDebtId);
        if (optional.isEmpty()) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("ForeignDebt is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<ForeignDebtDto>builder()
                .success(true)
                .message("OK")
                .data(foreignDebtMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<ForeignDebtDto> updateForeignDebt(Integer foreignDebtId, ForeignDebtDto dto) {
        List<ErrorDto> errors = foreignDebtValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        Optional<ForeignDebt> optional = foreignDebtRepository.findByForeignDebtIdAndDeletedAtIsNull(foreignDebtId);
        if (optional.isEmpty()) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("ForeignDebt is not found!")
                    .code(-3)
                    .build();
        }

        try {

            ForeignDebt foreignDebt = foreignDebtMapper.toEntity(dto);
            foreignDebt.setForeignDebtId(optional.get().getForeignDebtId());
            foreignDebt.setUpdatedAt(LocalDateTime.now());
            foreignDebtRepository.save(foreignDebt);

            return ResponseDto.<ForeignDebtDto>builder()
                    .success(true)
                    .message("OK")
                    .data(foreignDebtMapper.toDto(foreignDebt))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("ForeignDebt while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<ForeignDebtDto> deleteForeignDebt(Integer foreignDebtId) {
        Optional<ForeignDebt> optional = foreignDebtRepository.findByForeignDebtIdAndDeletedAtIsNull(foreignDebtId);
        if (optional.isEmpty()) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("ForeignDebt is not found!")
                    .code(-3)
                    .build();
        }
        try {
            ForeignDebt foreignDebt = optional.get();
            foreignDebt.setDeletedAt(LocalDateTime.now());
            foreignDebtRepository.save(foreignDebt);
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("OK")
                    .success(true)
                    .data(foreignDebtMapper.toDto(foreignDebt))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("ForeignDebt while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<Page<ForeignDebtDto>> getAdvancedSearch(Map<String, String> params) {
        Page<ForeignDebt> foreignDebts = this.foreignDebtRepositoryImpl.getAdvancedSearch(params);
        if (foreignDebts.isEmpty()) {
            return ResponseDto.<Page<ForeignDebtDto>>builder()
                    .message("ForeignDeb is not found")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Page<ForeignDebtDto>>builder()
                .success(true)
                .message("Ok")
                .data(foreignDebts.map(foreignDebtMapper::toDto))
                .build();
    }

}