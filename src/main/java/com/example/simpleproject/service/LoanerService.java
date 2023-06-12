package com.example.simpleproject.service;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.LoanerDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.model.Loaner;
import com.example.simpleproject.repository.LoanerRepository;
import com.example.simpleproject.repository.LoanerRepositoryImpl;
import com.example.simpleproject.service.mapper.LoanerMapper;
import com.example.simpleproject.service.validation.LoanerValidate;
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
public class LoanerService {

    private final LoanerMapper loanerMapper;
    private final LoanerRepository loanerRepository;
    private final LoanerValidate loanerValidate;
    private final LoanerRepositoryImpl loanerRepositoryImpl;


    public ResponseDto<LoanerDto> createLoaner(LoanerDto dto) {
        List<ErrorDto> errors = this.loanerValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        try {
            Loaner loaner = loanerMapper.toEntity(dto);
            loaner.setCreatedAt(LocalDateTime.now());
            loanerRepository.save(loaner);
            return ResponseDto.<LoanerDto>builder()
                    .success(true)
                    .message("Loaner successful created!")
                    .data(loanerMapper.toDto(loaner))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }

    }

    public ResponseDto<LoanerDto> getLoaner(Integer loanerId) {
        Optional<Loaner> optional = loanerRepository.findByLoanerIdAndDeletedAtIsNull(loanerId);
        if (optional.isEmpty()) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<LoanerDto>builder()
                .success(true)
                .message("OK")
                .data(loanerMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<LoanerDto> updateLoaner(Integer loanerId, LoanerDto dto) {
        List<ErrorDto> errors = loanerValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        Optional<Loaner> optional = loanerRepository.findByLoanerIdAndDeletedAtIsNull(loanerId);
        if (optional.isEmpty()) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner is not found!")
                    .code(-3)
                    .build();
        }

        try {

            Loaner loaner = loanerMapper.toEntity(dto);
            loaner.setLoanerId(optional.get().getLoanerId());
            loaner.setUpdatedAt(LocalDateTime.now());
            loanerRepository.save(loaner);

            return ResponseDto.<LoanerDto>builder()
                    .success(true)
                    .message("OK")
                    .data(loanerMapper.toDto(loaner))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<LoanerDto> deleteLoaner(Integer loanerId) {
        Optional<Loaner> optional = loanerRepository.findByLoanerIdAndDeletedAtIsNull(loanerId);
        if (optional.isEmpty()) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner is not found!")
                    .code(-3)
                    .build();
        }
        try {
            Loaner loaner = optional.get();
            loaner.setDeletedAt(LocalDateTime.now());
            loanerRepository.save(loaner);
            return ResponseDto.<LoanerDto>builder()
                    .message("OK")
                    .success(true)
                    .data(loanerMapper.toDto(loaner))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<Page<LoanerDto>> getAdvancedSearch(Map<String, String> params) {
        Page<LoanerDto> loaner = this.loanerRepositoryImpl.getAdvancedSearch(params)
                .map(this.loanerMapper::toDto);
        if (loaner.isEmpty()) {
            return ResponseDto.<Page<LoanerDto>>builder()
                    .message("Loaner is not found")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Page<LoanerDto>>builder()
                .success(true)
                .message("Ok")
                .data(loaner)
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


 */