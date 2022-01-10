package com.moroccanpixels.moroccanpixels.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public Image uploadImage(@ModelAttribute ImageRequest imageRequest){
        return imageService.uploadImage(imageRequest);
    }

    @GetMapping
    public List<Image> listImages(){
        return imageService.listImages();
    }

    @GetMapping(
            path="{imageId}",
            produces = {MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE}
    )
    public @ResponseBody byte[] getImage(@PathVariable("imageId") Long imageId) throws IOException {
        return imageService.getImage(imageId);
    }
    
    @DeleteMapping("{imageId}")
    public void deleteImage(@PathVariable(name="imageId") Long imageId){
        imageService.deleteImage(imageId);
    }

    @PutMapping("{imageId}")
    public Image updateImage(@PathVariable Long imageId,@ModelAttribute ImageRequest imageRequest){
        return imageService.updateImage(imageId,imageRequest);
    }
}
