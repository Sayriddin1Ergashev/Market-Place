package com.example.simpleproject.service;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.dto.UsersDto;
import com.example.simpleproject.model.Users;
import com.example.simpleproject.repository.UsersRepository;
import com.example.simpleproject.repository.UsersrepositoryImpl;
import com.example.simpleproject.service.mapper.UsersMapper;
import com.example.simpleproject.service.validation.UsersValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService {


    private final UsersMapper usersMapper;
    private final UsersRepository usersRepository;
    private final UsersValidate usersValidate;
    private final UsersrepositoryImpl usersRepositoryImpl;

    public ResponseDto<UsersDto> createUsers(UsersDto dto) {
        List<ErrorDto> errors = this.usersValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<UsersDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        try {
            Users users = usersMapper.toEntity(dto);
            users.setCreatedAt(LocalDateTime.now());
            usersRepository.save(users);
            return ResponseDto.<UsersDto>builder()
                    .success(true)
                    .message("Users successful created!")
                    .data(usersMapper.toDto(users))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UsersDto>builder()
                    .message("Users while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }

    }

    public ResponseDto<UsersDto> getUsers(Integer usersId) {
        Optional<Users> optional = usersRepository.findByUsersIdAndDeletedAtIsNull(usersId);
        if (optional.isEmpty()) {
            return ResponseDto.<UsersDto>builder()
                    .message("Users is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<UsersDto>builder()
                .success(true)
                .message("OK")
                .data(usersMapper.toDto(optional.get()))
                .build();
    }


    public ResponseDto<UsersDto> updateUsers(UsersDto dto, Integer usersId) {
        List<ErrorDto> errors = usersValidate.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<UsersDto>builder()
                    .message("Validation error")
                    .data(dto)
                    .code(-2)
                    .build();
        }

        Optional<Users> optional = usersRepository.findByUsersIdAndDeletedAtIsNull(usersId);
        if (optional.isEmpty()) {
            return ResponseDto.<UsersDto>builder()
                    .message("Users is not found!")
                    .code(-3)
                    .build();
        }

        try {

            Users users = usersMapper.toEntity(dto);
            users.setUsersId(optional.get().getUsersId());
            users.setCreatedAt(optional.get().getCreatedAt());
            users.setDeletedAt(optional.get().getDeletedAt());
            users.setUpdatedAt(LocalDateTime.now());
            usersRepository.save(users);

            return ResponseDto.<UsersDto>builder()
                    .success(true)
                    .message("OK")
                    .data(usersMapper.toDto(users))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<UsersDto>builder()
                    .message("Users while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<UsersDto> deleteUsers(Integer usersId) {
        Optional<Users> optional = usersRepository.findByUsersIdAndDeletedAtIsNull(usersId);
        if (optional.isEmpty()) {
            return ResponseDto.<UsersDto>builder()
                    .message("Users is not found!")
                    .code(-3)
                    .build();
        }
        try {
            Users users = optional.get();
            users.setDeletedAt(LocalDateTime.now());
            usersRepository.save(users);
            return ResponseDto.<UsersDto>builder()
                    .message("OK")
                    .success(true)
                    .data(usersMapper.toDto(users))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<UsersDto>builder()
                    .message("Users while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

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


}
