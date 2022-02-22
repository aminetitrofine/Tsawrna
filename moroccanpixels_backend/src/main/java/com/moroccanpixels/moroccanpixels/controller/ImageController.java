package com.moroccanpixels.moroccanpixels.controller;

import com.moroccanpixels.moroccanpixels.dto.ImageResponseDto;
import com.moroccanpixels.moroccanpixels.dto.ImageRequestDto;
import com.moroccanpixels.moroccanpixels.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

import static org.springframework.http.MediaType.*;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ImageResponseDto uploadImage(@ModelAttribute ImageRequestDto imageRequestDto){
        return imageService.uploadImage(imageRequestDto);
    }

    @GetMapping
    public Set<ImageResponseDto> listImages(){
        return imageService.listImages();
    }

    @GetMapping(path="{imageId}")
    public @ResponseBody ImageResponseDto getImage(@PathVariable("imageId") Long imageId){
        return imageService.getImage(imageId);
    }
    @GetMapping(
            path="{imageId}/view",
            produces = {IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE}
    )
    public @ResponseBody byte[] viewImage(@PathVariable("imageId") Long imageId) throws IOException {
        return imageService.viewImage(imageId);
    }
    
    @DeleteMapping("{imageId}")
    public void deleteImage(@PathVariable(name="imageId") Long imageId){
        imageService.deleteImage(imageId);
    }

    @PutMapping("{imageId}")
    public ImageResponseDto updateImage(@PathVariable Long imageId, @ModelAttribute ImageRequestDto imageRequestDto){
        return imageService.updateImage(imageId, imageRequestDto);
    }
    @PostMapping("{imageId}/keyword")
    public void mapKeywordToImage(@PathVariable Long imageId,@RequestBody String keyword){
        imageService.mapKeywordToImage(imageId,keyword);
    }

    @PostMapping("{imageId}/save")
    public void saveImage(@PathVariable Long imageId){
        imageService.saveImage(imageId);
    }
    @PostMapping("{imageId}/unsave")
    public void unsaveImage(@PathVariable Long imageId){
        imageService.unsaveImage(imageId);
    }
}
