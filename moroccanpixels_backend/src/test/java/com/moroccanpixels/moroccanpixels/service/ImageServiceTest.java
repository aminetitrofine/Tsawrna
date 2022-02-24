package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.auth.IAuthenticationFacade;
import com.moroccanpixels.moroccanpixels.model.entity.Image;
import com.moroccanpixels.moroccanpixels.model.entity.Keyword;
import com.moroccanpixels.moroccanpixels.model.entity.User;
import com.moroccanpixels.moroccanpixels.repository.ImageRepository;
import com.moroccanpixels.moroccanpixels.repository.KeywordRepository;
import com.moroccanpixels.moroccanpixels.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

public class ImageServiceTest {
    private final ImageRepository imageRepository = Mockito.mock(ImageRepository.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final KeywordRepository keywordRepository = Mockito.mock(KeywordRepository.class);
    private final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private final IAuthenticationFacade authenticationFacade = Mockito.mock(IAuthenticationFacade.class);

    @Test
    void uploadImage() {
        ImageService imageService = new ImageService(imageRepository,userRepository,keywordRepository,request,authenticationFacade);

        /*Assertions.assertThatThrownBy(() -> {
            imageService.uploadImage(new ImageRequestDto("C:\\Users\\User\\Downloads\\assali_affiche.jpg","affiche assali eitc"));
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("exception msg");*/
    }

    @Test
    void listImages() {
    }

    @Test
    void getImage() {
        Image imageMock = Mockito.mock(Image.class);
        Mockito.when(imageRepository.findById(5L)).thenReturn(Optional.of(imageMock));
        imageMock.setViewCount(imageMock.getViewCount()+1);
        //InputStream in = new FileInputStream(request.getServletContext().getRealPath(imageMock.getLocalPath()));
    }

    @Test
    void deleteImage() {
    }

    @Test
    void updateImage() {
    }

    @Test
    @DisplayName("Should map keyword to image")
    void shouldMapKeywordToImage() {
        Image image = new Image();
        Keyword keyword = new Keyword();

        Mockito.when(imageRepository.findById(3L)).thenReturn(Optional.of(image));
        Mockito.when(keywordRepository.findByName("sea")).thenReturn(Optional.of(keyword));
        keyword.addImage(image);
        keywordRepository.save(keyword);
        Mockito.verify(keywordRepository,Mockito.times(1)).save(ArgumentMatchers.any(Keyword.class));
        image.addKeyword(keyword);
        imageRepository.save(image);
        Mockito.verify(imageRepository,Mockito.times(1)).save(ArgumentMatchers.any(Image.class));
    }

    @Test
    @DisplayName("Should save image")
    void shouldSaveImage() {
        String username = "user";
        User user = Mockito.mock(User.class); //User user = new User();
        Image image = Mockito.mock(Image.class); //Image image = new Image();
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        Mockito.when(imageRepository.findById(1L)).thenReturn(Optional.of(image));
        user.addSavedImage(image);
        //Mockito.verify(user,Mockito.times(1)).addSavedImage(ArgumentMatchers.any(Image.class));

    }

    @Test
    @DisplayName("Should unsave image if exists")
    void shouldUnsaveImage() {
        String username = "user";
        User user = new User();
        Image image = new Image();
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        Mockito.when(imageRepository.findById(1L)).thenReturn(Optional.of(image));
        user.removeSavedImage(image);
    }
}