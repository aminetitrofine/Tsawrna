package com.moroccanpixels.moroccanpixels.config;

import com.moroccanpixels.moroccanpixels.model.ImageType;
import com.moroccanpixels.moroccanpixels.model.entity.Category;
import com.moroccanpixels.moroccanpixels.model.entity.Image;
import com.moroccanpixels.moroccanpixels.model.entity.User;
import com.moroccanpixels.moroccanpixels.repository.CategoryRepository;
import com.moroccanpixels.moroccanpixels.repository.ImageRepository;
import com.moroccanpixels.moroccanpixels.repository.UserRepository;
import com.moroccanpixels.moroccanpixels.security.ApplicationUserRole;
import com.moroccanpixels.moroccanpixels.model.StatusType;
import com.moroccanpixels.moroccanpixels.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Configuration
public class AppConfig {
    private final PasswordEncoder passwordEncoder;
    private final ImageRepository imageRepository;
    private final ImageConfig imageConfig;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public AppConfig(PasswordEncoder passwordEncoder, ImageRepository imageRepository, ImageConfig imageConfig, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.passwordEncoder = passwordEncoder;
        this.imageRepository = imageRepository;
        this.imageConfig = imageConfig;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            User hamza = new User(
                    "benyazidhamza",
                    "benyazidhamza969@gmail.com",
                    "Hamza",
                    "Benyazid",
                    passwordEncoder.encode("12345678"),
                    ApplicationUserRole.ADMIN,
                    StatusType.CONFIRMED
            );
            User amine = new User(
                    "amine",
                    "aminetitro@gmail.com",
                    "Amine",
                    "Titrofine",
                    passwordEncoder.encode("password"),
                    ApplicationUserRole.CONTRIBUTOR,
                    StatusType.ARCHIVED
            );
            Category building = new Category(
            );
            building.setName("Building");
            building.setDescription("Test");
            hamza=userRepository.save(hamza);
            amine=userRepository.save(amine);
            building= categoryRepository.save(building);
            saveSomeImages(hamza);
        };
    }
    public void saveSomeImages(User owner){
        File imageDirectory = new File(imageConfig.getInitDirectory());
        if(!imageDirectory.exists()) return;
        if(imageDirectory.list()==null) return;
        List<File> images = List.of(Objects.requireNonNull(imageDirectory.listFiles()));
        Instant instant = Instant.now();
        String fileName;
        String uploadsDir;
        String realPathToUploads;
        Image imageEntity=new Image();
        Long id = 1L;
        for(File image : images){
            imageEntity.setId(id);
            imageEntity.setOwner(owner);
            imageEntity.setUploadedAt(instant);
            imageEntity.setLastModified(instant);
            imageEntity.setDescription(image.getName());
            imageEntity.setDownloadCount(0);
            imageEntity.setType(ImageType.fromValue(ImageUtils.getFileExtension(image)));
            imageRepository.save(imageEntity);
            fileName = imageEntity.getId()+"."+imageEntity.getType().value();
            uploadsDir = "/uploads/images/"+imageEntity.getOwner().getUsername()+"/";
            realPathToUploads =  imageConfig.getDirectory()+uploadsDir;
            ImageUtils.saveImage(image,realPathToUploads,fileName);
            id++;
        }
    }

    @EventListener(ContextClosedEvent.class)
    public void clearImages(){
        ImageUtils.deleteDirectory(imageConfig.getDirectory());
    }
}
