package com.example.simpleproject.controller;

import com.example.simpleproject.dto.ImageDto;
import com.example.simpleproject.dto.ResponseDto;
import com.example.simpleproject.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(value = ("image"))
@RequiredArgsConstructor
public class ImageController {

private final ImageService imageService;

@PostMapping(value = ("/create"))
    public ResponseDto<ImageDto>createImage(@RequestBody ImageDto dto){
    return imageService.createImage(dto);
}

@GetMapping(value = ("/get/{id}"))
    public ResponseDto<ImageDto>getImage(@PathVariable("id") Integer imageId){
    return imageService.getImage(imageId);
}

@PutMapping(value = ("/update/{id}"))
    public ResponseDto<ImageDto>updateImage(@PathVariable("id") Integer imageId,
                                            @RequestBody ImageDto dto){
return imageService.updateImage(imageId,dto);
}

@DeleteMapping(value = ("/delete/{id}"))
    public ResponseDto<ImageDto>deleteImage(@PathVariable("id") Integer imageId){
    return imageService.deleteImage(imageId);
}

    @GetMapping(value = ("/get-advanced-search"))
    public ResponseDto<Page<ImageDto>> getAdvancedSearch(@RequestParam Map<String,String> params) {
        return imageService.getAdvancedSearch(params);
    }



}
