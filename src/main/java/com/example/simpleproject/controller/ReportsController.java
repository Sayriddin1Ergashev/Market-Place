package com.example.simpleproject.controller;

import com.example.simpleproject.dto.ReportsDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.service.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = ("reports"))
@RequiredArgsConstructor
public class ReportsController {

    private final ReportsService reportsService;



    @PostMapping(value = "/create")
    public ResponseDto<ReportsDto> createReportd(@RequestBody ReportsDto dto) {
        return reportsService.createReports(dto);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseDto<ReportsDto> getReports(@PathVariable("id") Integer reportsId) {
        return reportsService.getReports(reportsId);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseDto<ReportsDto> updateReports(@PathVariable("id") Integer reportsId,
                                               @RequestBody ReportsDto dto){
        return reportsService.updateReports(reportsId,dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseDto<ReportsDto>deleteReports(@PathVariable("id") Integer reportsId){
        return reportsService.deleteReports(reportsId);
    }

    /*@GetMapping(value = ("/get-advanced-search"))
    public ResponseDto<Page<ReportsDto>> getAdvancedSearch(@RequestParam Map<String,String> params) {
        return reportsService.getAdvancedSearch(params);
    }

    @GetMapping(value = ("/get-basic-search"))
    public ResponseDto<Page<ReportsDto>> getBasicSearch(@RequestParam Map<String, String> params) {
        return reportsService.getBasicSearch(params);
    }*/
}
