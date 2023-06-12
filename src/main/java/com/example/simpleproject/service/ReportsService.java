package com.example.simpleproject.service;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ReportsDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.model.Reports;
import com.example.simpleproject.repository.ReportsRepository;
import com.example.simpleproject.repository.ReportsRepositoryImpl;
import com.example.simpleproject.service.mapper.ReportsMapper;
import com.example.simpleproject.service.validation.ReportsValidate;
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
public class ReportsService {

    private final ReportsMapper reportsMapper;
    private final ReportsRepository reportsRepository;
    private final ReportsValidate reportsValidate;
    private final ReportsRepositoryImpl reportsRepositoryImpl;
    public ResponseDto<ReportsDto> createReports(ReportsDto dto) {
        List<ErrorDto> errors = this.reportsValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ReportsDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        try {
            Reports reports = reportsMapper.toEntity(dto);
            reports.setCreatedAt(LocalDateTime.now());
            reportsRepository.save(reports);
            return ResponseDto.<ReportsDto>builder()
                    .success(true)
                    .message("Reports successful created!")
                    .data(reportsMapper.toDto(reports))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ReportsDto>builder()
                    .message("Reports while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<ReportsDto> getReports(Integer reportsId) {
        Optional<Reports> optional = reportsRepository.findByReportsIdAndDeletedAtIsNull(reportsId);
        if (optional.isEmpty()) {
            return ResponseDto.<ReportsDto>builder()
                    .message("Reports is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<ReportsDto>builder()
                .success(true)
                .message("OK")
                .data(reportsMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<ReportsDto> updateReports(Integer reportsId, ReportsDto dto) {
        List<ErrorDto> errors = reportsValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ReportsDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        Optional<Reports> optional = reportsRepository.findByReportsIdAndDeletedAtIsNull(reportsId);
        if (optional.isEmpty()) {
            return ResponseDto.<ReportsDto>builder()
                    .message("Reports is not found!")
                    .code(-3)
                    .build();
        }

        try {

            Reports reports = reportsMapper.toEntity(dto);
            reports.setReportsId(optional.get().getReportsId());
            reports.setUpdatedAt(LocalDateTime.now());
            reportsRepository.save(reports);

            return ResponseDto.<ReportsDto>builder()
                    .success(true)
                    .message("OK")
                    .data(reportsMapper.toDto(reports))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<ReportsDto>builder()
                    .message("Reports while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<ReportsDto> deleteReports(Integer reportsId) {
        Optional<Reports> optional = reportsRepository.findByReportsIdAndDeletedAtIsNull(reportsId);
        if (optional.isEmpty()) {
            return ResponseDto.<ReportsDto>builder()
                    .message("Reports is not found!")
                    .code(-3)
                    .build();
        }
        try {
            Reports reports = optional.get();
            reports.setDeletedAt(LocalDateTime.now());
            reportsRepository.save(reports);
            return ResponseDto.<ReportsDto>builder()
                    .message("OK")
                    .success(true)
                    .data(reportsMapper.toDto(reports))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<ReportsDto>builder()
                    .message("Reports while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<Page<ReportsDto>> getAdvancedSearch(Map<String, String> params) {
        Page<ReportsDto> reports = this.reportsRepositoryImpl.getAdvancedSearch(params)
                .map(this.reportsMapper::toDto);
        if (reports.isEmpty()) {
            return ResponseDto.<Page<ReportsDto>>builder()
                    .message("Reports is not found")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Page<ReportsDto>>builder()
                .success(true)
                .message("Ok")
                .data(reports)
                .build();
    }
}
