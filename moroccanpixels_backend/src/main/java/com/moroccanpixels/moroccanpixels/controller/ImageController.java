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
@RequestMapping("")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("image")
    public ImageResponseDto uploadImage(@ModelAttribute ImageRequestDto imageRequestDto){
        return imageService.uploadImage(imageRequestDto);
    }

    @GetMapping("image")
    public Set<ImageResponseDto> listImages(){
        return imageService.listImages();
    }



    @GetMapping(path="image/{imageId}")
    public @ResponseBody ImageResponseDto getImage(@PathVariable("imageId") Long imageId){
        return imageService.getImage(imageId);
    }
    @GetMapping(
            path="image/{imageId}/view",
            produces = {IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE}
    )
    public @ResponseBody byte[] viewImage(@PathVariable("imageId") Long imageId) throws IOException {
        return imageService.viewImage(imageId);
    }
    
    @DeleteMapping("image/{imageId}")
    public void deleteImage(@PathVariable(name="imageId") Long imageId){
        imageService.deleteImage(imageId);
    }

    @PutMapping("image/{imageId}")
    public ImageResponseDto updateImage(@PathVariable Long imageId, @ModelAttribute ImageRequestDto imageRequestDto){
        return imageService.updateImage(imageId, imageRequestDto);
    }
    @PostMapping("image/{imageId}/keyword")
    public void mapKeywordToImage(@PathVariable Long imageId,@RequestBody String keyword){
        imageService.mapKeywordToImage(imageId,keyword);
    }

    @PostMapping("image/{imageId}/save")
    public void saveImage(@PathVariable Long imageId){
        imageService.saveImage(imageId);
    }
    @PostMapping("image/{imageId}/unsave")
    public void unsaveImage(@PathVariable Long imageId){
        imageService.unsaveImage(imageId);
    }

    @GetMapping("{username}/gallery")
    public Set<ImageResponseDto> getUserImages(@PathVariable String username){
        return imageService.getUserImages(username);
    }

    @GetMapping("search")
    public Set<ImageResponseDto> searchImages(@RequestParam("q") String q){
        return imageService.searchImages(q);
    }
}
