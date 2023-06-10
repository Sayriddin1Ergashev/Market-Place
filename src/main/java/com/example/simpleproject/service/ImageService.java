package com.example.simpleproject.service;

import com.example.simpleproject.dto.ErrorDto;
import com.example.simpleproject.dto.ImageDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.model.Image;
import com.example.simpleproject.repository.ImageRepository;
import com.example.simpleproject.service.mapper.ImageMapper;
import com.example.simpleproject.service.validation.ImageValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;
    private final ImageValidate imageValidate;
    public ResponseDto<ImageDto> createImage(ImageDto dto) {
        List<ErrorDto> errors = imageValidate.validate(dto);
            if (!errors.isEmpty()){
                return ResponseDto.<ImageDto>builder()
                        .message("Validation error")
                        .code(-2)
                        .data(dto)
                        .build();
            }
            try {
                Image image = imageMapper.toEntity(dto);
                image.setCreatedAt(LocalDateTime.now());
                imageRepository.save(image);
                return ResponseDto.<ImageDto>builder()
                        .message("OK")
                        .success(true)
                        .data(imageMapper.toDto(image))
                        .build();
            } catch (Exception c) {
                return ResponseDto.<ImageDto>builder()
                        .code(-3)
                        .message("Image while saving error: " + c.getMessage())
                        .build();

            }
    }

    public ResponseDto<ImageDto> getImage(Integer imageId) {
        try {
            Optional<Image> optional=imageRepository.findByImageIdAndDeletedAtIsNull(imageId);
            if (optional.isEmpty()) {
                return ResponseDto.<ImageDto>builder()
                        .message("Image is not found!")
                        .code(-1)
                        .build();
            }
            return ResponseDto.<ImageDto>builder()
                    .message("OK")
                    .success(true)
                    .data(imageMapper.toDto(optional.get()))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<ImageDto>builder()
                    .code(-3)
                    .message("Database Error: " + c.getMessage())
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<ImageDto> updateImage(Integer imageId, ImageDto dto) {
        List<ErrorDto> errors = imageValidate.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<ImageDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(dto)
                    .build();
        }
        Optional<Image> optional = imageRepository.findByImageIdAndDeletedAtIsNull(imageId);
        if (optional.isEmpty()) {
            return ResponseDto.<ImageDto>builder()
                    .code(-1)
                    .message("Image is not found!")
                    .build();
        }
        try {
            Image image = imageMapper.toEntity(dto);
            image.setImageId(optional.get().getImageId());
            image.setCreatedAt(optional.get().getCreatedAt());
            image.setDeletedAt(optional.get().getDeletedAt());
            image.setUpdatedAt(LocalDateTime.now());
            imageRepository.save(image);
            return ResponseDto.<ImageDto>builder()
                    .message("OK")
                    .success(true)
                    .data(imageMapper.toDto(image))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ImageDto>builder()
                    .message("Image while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<ImageDto> deleteImage(Integer imageId) {
        Optional<Image> optional = imageRepository.findByImageIdAndDeletedAtIsNull(imageId);
        if (optional.isEmpty()) {
            return ResponseDto.<ImageDto>builder()
                    .code(-1)
                    .message("Image is not found!")
                    .build();
        }
        try {
            Image image = optional.get();
            image.setDeletedAt(LocalDateTime.now());
            imageRepository.save(image);
            return ResponseDto.<ImageDto>builder()
                    .success(true)
                    .message("OK")
                    .data(imageMapper.toDto(image))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ImageDto>builder()
                    .message("Image while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }


}
