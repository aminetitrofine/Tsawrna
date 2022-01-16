package com.moroccanpixels.moroccanpixels.image;

import com.moroccanpixels.moroccanpixels.dto.ImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ImageDto uploadImage(@ModelAttribute ImageRequest imageRequest){
        return imageService.uploadImage(imageRequest);
    }

    @GetMapping
    public Set<ImageDto> listImages(){
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
    public ImageDto updateImage(@PathVariable Long imageId,@ModelAttribute ImageRequest imageRequest){
        return imageService.updateImage(imageId,imageRequest);
    }
    @PostMapping("{imageId}/keyword")
    public void mapKeywordToImage(@PathVariable Long imageId,@RequestBody String keyword){
        imageService.mapKeywordToImage(imageId,keyword);
    }

    @PostMapping("{imageId}/save")
    public void saveImage(@PathVariable Long imageId,@RequestBody String username){
        imageService.saveImage(imageId,username);
    }
    @PostMapping("{imageId}/unsave")
    public void unsaveImage(@PathVariable Long imageId,@RequestBody String username){
        imageService.unsaveImage(imageId,username);
    }
}
