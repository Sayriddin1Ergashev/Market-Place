package com.example.simpleproject.service.validation;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ImageDto;
import com.example.simpleproject.repository.ImageRepository;
import com.example.simpleproject.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageValidate {
    private final ImageRepository imageRepository;
    public List<ErrorDto> validate(ImageDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (imageRepository.existsByPath(dto.getPath())){
            errors.add(new ErrorDto("path","Path already exist."));
        }
        if (imageRepository.existsByType(dto.getType())){
            errors.add(new ErrorDto("type","Type already exist."));
        }
        if (imageRepository.existsBySize(dto.getSize())){
            errors.add(new ErrorDto("size","Size already exist."));
        }
        if (imageRepository.existsByToken(dto.getToken())){
            errors.add(new ErrorDto("token","Token already exist."));
        }
        return errors;

    }


}
