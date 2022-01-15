package com.moroccanpixels.moroccanpixels.image;

import com.moroccanpixels.moroccanpixels.keyword.Keyword;
import com.moroccanpixels.moroccanpixels.keyword.KeywordRepository;
import com.moroccanpixels.moroccanpixels.user.User;
import com.moroccanpixels.moroccanpixels.user.UserRepository;
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
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final KeywordRepository keywordRepository;
    private final HttpServletRequest request;

    @Autowired
    public ImageService(ImageRepository imageRepository, UserRepository userRepository, KeywordRepository keywordRepository, HttpServletRequest request) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.keywordRepository = keywordRepository;
        this.request = request;
    }
    @Transactional
    public Image uploadImage(ImageRequest imageRequest) {
        Instant instant = Instant.now();
        MultipartFile file = imageRequest.getFile();
        if(!Objects.requireNonNull(file.getContentType()).startsWith("image")){
            throw new IllegalStateException("This not an image");
        }
        Image image = new Image();

        //setting description
        image.setDescription(imageRequest.getDescription());
        //setting image type
        image.setType(ImageType.fromContentType(file.getContentType()));
        //setting owner
        User owner = userRepository.findByUsername(imageRequest.getUsername()).orElseThrow(()-> new IllegalStateException("User doesnt exist"));
        image.setOwner(owner);
        //setting uploadedAt and lastModified
        image.setUploadedAt(instant);
        image.setLastModified(instant);
        //saving image
        imageRepository.save(image);
        String fileName = image.getId()+"."+image.getType().value();
        String uploadsDir = "/uploads/images/"+image.getOwner().getUsername()+"/";
        String realPathToUploads =  request.getServletContext().getRealPath(uploadsDir);
        System.out.println(realPathToUploads);
        ImageUtils.saveImage(file,realPathToUploads,fileName);
        return  image;
    }

    public List<Image> listImages() {
        return imageRepository.findAll();
    }

    public byte[] getImage(Long imageId) throws IOException {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(()->new IllegalStateException("image with id "+imageId+" not found."));
        System.out.println(request.getServletContext().getRealPath(image.getPath()));
        InputStream in = new FileInputStream(request.getServletContext().getRealPath(image.getPath()));
        return IOUtils.toByteArray(in);
    }

    public void deleteImage(Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(()->new IllegalStateException("image not found"));
        //delete file
        File file = new File(request.getServletContext().getRealPath(image.getPath()));
        if(file.delete()){
            System.out.println("image "+imageId+" deleted");
        }else{
            throw new IllegalStateException("failed to delete image");
        }
        //delete image from database
        imageRepository.delete(image);
    }

    public Image updateImage(Long imageId,ImageRequest imageRequest) {
        Instant instant = Instant.now();
        Image image = imageRepository.findById(imageId)
                .orElseThrow(()->new IllegalStateException("image with id "+imageId+" not found"));
        String file1Name = imageId+"."+image.getType().value();
        //verifying ownership
        if(!imageRequest.getUsername().equals(image.getOwner().getUsername()))
            throw new IllegalStateException("You can't update this image, you are not the owner");

        //updating description
        if (imageRequest.getDescription()!=null)
            image.setDescription(imageRequest.getDescription());

        //updating file
        MultipartFile file = imageRequest.getFile();
        if(file==null) return image;

        //updating image type
        if(!Objects.requireNonNull(file.getContentType()).startsWith("image")){
            throw new IllegalStateException("This is not an image");
        }
        image.setType(ImageType.fromContentType(file.getContentType()));

        //setting uploadedAt and lastModified
        image.setLastModified(instant);

        //saving image
        imageRepository.save(image);
        String file2Name = imageId+"."+image.getType().value();
        String uploadsDir = "/uploads/images/"+image.getOwner().getUsername()+"/";
        String realPathToUploads =  request.getServletContext().getRealPath(uploadsDir);
        System.out.println(realPathToUploads);
        ImageUtils.replaceImage(file,realPathToUploads,file1Name,file2Name);
        return  image;
    }

    public void mapKeywordToImage(Long imageId,String kw) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(()->new IllegalStateException("Image with id "+imageId+" not found"));
        Keyword keyword = keywordRepository.findByName(kw).orElse(new Keyword(kw));
        image.addKeyword(keyword);
        imageRepository.save(image);
    }
}
