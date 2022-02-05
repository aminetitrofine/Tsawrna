package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.auth.IAuthenticationFacade;
import com.moroccanpixels.moroccanpixels.config.ImageConfig;
import com.moroccanpixels.moroccanpixels.dto.ImageResponseDto;
import com.moroccanpixels.moroccanpixels.model.entity.Keyword;
import com.moroccanpixels.moroccanpixels.repository.ImageRepository;
import com.moroccanpixels.moroccanpixels.dto.ImageRequestDto;
import com.moroccanpixels.moroccanpixels.model.ImageType;
import com.moroccanpixels.moroccanpixels.model.entity.Image;
import com.moroccanpixels.moroccanpixels.repository.KeywordRepository;
import com.moroccanpixels.moroccanpixels.mapper.EntityToDto;
import com.moroccanpixels.moroccanpixels.model.entity.User;
import com.moroccanpixels.moroccanpixels.repository.UserRepository;
import com.moroccanpixels.moroccanpixels.utils.ImageUtils;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final KeywordRepository keywordRepository;
    private final HttpServletRequest request;
    private final IAuthenticationFacade authenticationFacade;
    private final ImageConfig imageConfig;

    @Autowired
    public ImageService(ImageRepository imageRepository, UserRepository userRepository, KeywordRepository keywordRepository, HttpServletRequest request, IAuthenticationFacade authenticationFacade, ImageConfig imageConfig) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.keywordRepository = keywordRepository;
        this.request = request;
        this.authenticationFacade = authenticationFacade;
        this.imageConfig = imageConfig;
    }
    public ImageResponseDto uploadImage(ImageRequestDto imageRequestDto) {
        Instant instant = Instant.now();
        MultipartFile file = imageRequestDto.getFile();
        if(!Objects.requireNonNull(file.getContentType()).startsWith("image")){
            throw new IllegalStateException("This not an image");
        }
        Image image = new Image();

        //setting description
        image.setDescription(imageRequestDto.getDescription());
        //setting image type
        image.setType(ImageType.fromContentType(file.getContentType()));
        //setting owner
        String username = authenticationFacade.getAuthentication().getName();
        User owner = userRepository.findByUsername(username).orElseThrow(()-> new IllegalStateException("User doesnt exist"));
        image.setOwner(owner);
        //setting other parameters
        image.setUploadedAt(instant);
        image.setLastModified(instant);
        image.setDownloadCount(0);
        image.setViewCount(0);
        //saving image
        imageRepository.save(image);
        String fileName = image.getId()+"."+image.getType().value();
        String uploadsDir = "/uploads/images/"+image.getOwner().getUsername()+"/";
        String realPathToUploads =  imageConfig.getDirectory()+uploadsDir;
        System.out.println(realPathToUploads);
        ImageUtils.saveImage(file,realPathToUploads,fileName);
        return EntityToDto.imageEntityToDto(image);
    }

    public Set<ImageResponseDto> listImages() {
        return EntityToDto.imageEntityToDto(imageRepository.findAll());
    }

    @Transactional
    public ImageResponseDto getImage(Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(()->new IllegalStateException("image with id "+imageId+" not found."));
        String authenticatedUsername = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findByUsername(authenticatedUsername)
                .orElseThrow(()-> new IllegalStateException("user with username %s doesn't exist."));
        image.addViewedByUser(user);
        return EntityToDto.imageEntityToDto(image);
    }
    @Transactional
    public byte[] viewImage(Long imageId) throws IOException {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(()->new IllegalStateException("image with id "+imageId+" not found."));
        InputStream in = new FileInputStream(request.getServletContext().getRealPath(image.getLocalPath()));
        return IOUtils.toByteArray(in);
    }

    public void deleteImage(Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(()->new IllegalStateException("image not found"));
        //delete file
        File file = new File(request.getServletContext().getRealPath(image.getLocalPath()));
        if(file.delete()){
            System.out.println("image "+imageId+" deleted");
        }else{
            throw new IllegalStateException("failed to delete image");
        }
        //delete image from database
        imageRepository.delete(image);
    }

    @Transactional
    public ImageResponseDto updateImage(Long imageId, ImageRequestDto imageRequestDto) {
        Instant instant = Instant.now();
        Image image = imageRepository.findById(imageId)
                .orElseThrow(()->new IllegalStateException("image with id "+imageId+" not found"));
        String file1Name = imageId+"."+image.getType().value();
        //verifying ownership
        String username = authenticationFacade.getAuthentication().getName();
        if(!username.equals(image.getOwner().getUsername()))
            throw new IllegalStateException("You can't update this image, you are not the owner");

        //updating description
        if (imageRequestDto.getDescription()!=null)
            image.setDescription(imageRequestDto.getDescription());

        //updating file
        MultipartFile file = imageRequestDto.getFile();
        if(file==null) return EntityToDto.imageEntityToDto(image);

        //updating image type
        if(!Objects.requireNonNull(file.getContentType()).startsWith("image")){
            throw new IllegalStateException("This is not an image");
        }
        image.setType(ImageType.fromContentType(file.getContentType()));

        //setting uploadedAt and lastModified
        image.setLastModified(instant);

        //saving image
        String file2Name = imageId+"."+image.getType().value();
        String uploadsDir = "/uploads/images/"+image.getOwner().getUsername()+"/";
        String realPathToUploads =  imageConfig.getDirectory()+uploadsDir;
        System.out.println(realPathToUploads);
        ImageUtils.replaceImage(file,realPathToUploads,file1Name,file2Name);
        return  EntityToDto.imageEntityToDto(image);
    }

    @Transactional
    public void mapKeywordToImage(Long imageId,String kw) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(()->new IllegalStateException("Image with id "+imageId+" not found"));
        Keyword keyword = keywordRepository.findByName(kw).orElse(new Keyword(kw));
        keyword.addImage(image);
        keywordRepository.save(keyword);
        image.addKeyword(keyword);
        imageRepository.save(image);
    }
    @Transactional
    public void saveImage(Long imageId) {
        String username = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new IllegalStateException("user "+username+" not found"));
        Image image = imageRepository.findById(imageId)
                .orElseThrow(()->new IllegalStateException("image with id "+imageId+"not found"));
        user.addSavedImage(image);
    }

    @Transactional
    public void unsaveImage(Long imageId) {
        String username = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new IllegalStateException("user "+username+" not found"));
        Image image = imageRepository.findById(imageId)
                .orElseThrow(()->new IllegalStateException("image with id "+imageId+"not found"));
        user.removeSavedImage(image);
    }
}
