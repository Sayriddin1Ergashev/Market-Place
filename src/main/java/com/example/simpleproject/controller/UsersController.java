package com.example.simpleproject.controller;

import com.example.simpleproject.dto.ProductDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.dto.UsersDto;
import com.example.simpleproject.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = ("users"))
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
@GetMapping(value = "get-page")
@Operation(
        tags = "Simple Pagination project",
        summary = "Your summary get page by users method.",
        description = "Your description this method."
        , responses = @ApiResponse(
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = ProductDto.class
                        )
                )
        )
        , parameters = {@Parameter(
                 name = "size",
                 example = "1"
         ), @Parameter(
                 )
         }
     //   ,hidden = true
)

    @PostMapping(value = ("/create"))
    public ResponseDto<UsersDto> createUsers(@RequestBody UsersDto dto) {
        return usersService.createUsers(dto);
    }

    @GetMapping(value = ("/get-advanced-search"))
    public ResponseDto<Page<UsersDto>> getAdvancedSearch(@RequestParam Map<String,String> params) {
        return usersService.getAdvancedSearch(params);
    }

    @GetMapping(value = ("/get-basic-search"))
    public ResponseDto<Page<UsersDto>> getBasicSearch(@RequestParam Map<String, String> params) {
        return usersService.getBasicSearch(params);
    }

    @GetMapping(value = ("/get/{id}"))
    public ResponseDto<UsersDto> getUsers(@PathVariable("id") Integer usersId) {
        return usersService.getUsers(usersId);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseDto<UsersDto> updateUsers(@PathVariable("id") Integer usersId,
                                             @RequestBody UsersDto dto) {
        return usersService.updateUsers(dto, usersId);
    }

    @DeleteMapping(value = ("/delete/{id}"))
    public ResponseDto<UsersDto> deleteUsers(@PathVariable("id") Integer usersId) {
        return usersService.deleteUsers(usersId);
    }
}
