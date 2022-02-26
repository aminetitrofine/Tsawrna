package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.auth.AuthenticationFacade;
import com.moroccanpixels.moroccanpixels.config.ImageConfig;
import com.moroccanpixels.moroccanpixels.dto.ImageResponseDto;
import com.moroccanpixels.moroccanpixels.mapper.EntityToDto;
import com.moroccanpixels.moroccanpixels.model.ImageType;
import com.moroccanpixels.moroccanpixels.model.StatusType;
import com.moroccanpixels.moroccanpixels.model.entity.Category;
import com.moroccanpixels.moroccanpixels.model.entity.Image;
import com.moroccanpixels.moroccanpixels.model.entity.Keyword;
import com.moroccanpixels.moroccanpixels.model.entity.User;
import com.moroccanpixels.moroccanpixels.repository.ImageRepository;
import com.moroccanpixels.moroccanpixels.repository.KeywordRepository;
import com.moroccanpixels.moroccanpixels.repository.UserRepository;
import com.moroccanpixels.moroccanpixels.security.ApplicationUserRole;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class ImageServiceTest {
    private final ImageRepository imageRepository = mock(ImageRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final KeywordRepository keywordRepository = mock(KeywordRepository.class);
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final AuthenticationFacade authenticationFacade = mock(AuthenticationFacade.class);
    private final ImageConfig imageConfig = mock(ImageConfig.class);

    @Test
    void shouldListImages() {
        //given
        User user = new User("username","user@gmail.com","first name","last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image1 = new Image(5L,user,"my_path", Instant.now(),Instant.now(),"description1",0, new Category(), 0, 0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());
        Image image2 = new Image(6L,user,"my_path", Instant.now(),Instant.now(),"description2",0, new Category(), 0, 0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());

        List<Image> imageList = new ArrayList<>();
        imageList.add(image1); imageList.add(image2);

        Set<ImageResponseDto> imageResponseDtoSet = new HashSet<>();
        imageResponseDtoSet.add(EntityToDto.imageEntityToDto(image1));
        imageResponseDtoSet.add(EntityToDto.imageEntityToDto(image2));

        //when
        when(imageRepository.findAll()).thenReturn(imageList);

        //then
        assertThat(EntityToDto.imageEntityToDto(imageList)).isEqualTo(imageResponseDtoSet);
    }

    @Test
    void shouldGetImage() {
        User user = new User("username","user@gmail.com","first name","last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image = new Image(5L,user,"my_path", Instant.now(),Instant.now(),"description", 0, new Category(), 0, 0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());
        String authenticatedUsername = user.getUsername();

        when(imageRepository.findById(5L)).thenReturn(Optional.of(image));
        when(authenticationFacade.getAuthenticatedUsername()).thenReturn(authenticatedUsername);
        assertThat(authenticatedUsername).isNotEqualTo("anonymousUser");
        when(userRepository.findByUsername(authenticatedUsername)).thenReturn(Optional.of(user));
        image.addViewedByUser(user);

        ImageResponseDto imageResponseDto = EntityToDto.imageEntityToDto(image);
        assertThat(EntityToDto.imageEntityToDto(image)).isEqualTo(imageResponseDto);
    }

    @Test
    void shouldDeleteImage() {
        User user = new User("username","user@gmail.com","first name","last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image = new Image(1L,user,"image_path", Instant.now(),Instant.now(),"description",0,new Category(),0,0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());
        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));
        String directory = "my_directory";
        when(imageConfig.getDirectory()).thenReturn(directory);
        File file = new File(directory + image.getLocalPath());
        file.delete();
        //when
        imageRepository.delete(image);
        //then
        ArgumentCaptor<Image> imageArgumentCaptor = ArgumentCaptor.forClass(Image.class);
        verify(imageRepository).delete(imageArgumentCaptor.capture());

        Image capturedImage = imageArgumentCaptor.getValue();
        assertThat(capturedImage).isEqualTo(image);
    }

    @Test
    void shouldMapKeywordToImage() {
        //given
        User user = new User("username","user@gmail.com","first name","last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image = new Image(1L,user,"image_path", Instant.now(),Instant.now(),"description",0,new Category(),0,0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());
        Keyword keyword = new Keyword("sea");

        //when
        when(imageRepository.findById(3L)).thenReturn(Optional.of(image));
        when(keywordRepository.findByName("sea")).thenReturn(Optional.of(keyword));

        //then
        keyword.addImage(image);
        keywordRepository.save(keyword);
        //verify if the keyword save method was done correctly
        ArgumentCaptor<Keyword> keywordArgumentCaptor = ArgumentCaptor.forClass(Keyword.class);
        verify(keywordRepository).save(keywordArgumentCaptor.capture());
        //check if the captured keyword is equal to the given keyword
        Keyword capturedKeyword = keywordArgumentCaptor.getValue();
        assertThat(capturedKeyword).isEqualTo(keyword);

        image.addKeyword(keyword);
        imageRepository.save(image);
        //verify if the image save method was done correctly
        ArgumentCaptor<Image> imageArgumentCaptor = ArgumentCaptor.forClass(Image.class);
        verify(imageRepository).save(imageArgumentCaptor.capture());
        //check if the captured image is equal to the given image
        Image capturedImage = imageArgumentCaptor.getValue();
        assertThat(capturedImage).isEqualTo(image);
    }

    @Test
    void shouldSaveImage() {
        //given
        String username = "username";
        User user = new User("username","user@gmail.com","first name","last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image = new Image(1L,user,"image_path", Instant.now(),Instant.now(),"description",0,new Category(),0,0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());

        //when
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));

        //then
        user.addSavedImage(image);

    }

    @Test
    void shouldUnsaveImage() {
        //given
        String username = "username";
        User user = new User("username","user@gmail.com","first name","last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image = new Image(1L,user,"image_path", Instant.now(),Instant.now(),"description",0,new Category(),0,0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());
        //when
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));

        //then
        user.removeSavedImage(image);
    }

    @Test
    void shouldGetUserImages() {
        //given
        User user = new User("username","user@gmail.com","first name","last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image1 = new Image(5L,user,"my_path", Instant.now(),Instant.now(),"description1",0,new Category(),0,0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());
        Image image2 = new Image(6L,user,"my_path", Instant.now(),Instant.now(),"description2",0,new Category(),0,0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());

        List<Image> imageList = new ArrayList<>();
        imageList.add(image1); imageList.add(image2);

        Set<ImageResponseDto> imageResponseDtoSet = new HashSet<>();
        imageResponseDtoSet.add(EntityToDto.imageEntityToDto(image1));
        imageResponseDtoSet.add(EntityToDto.imageEntityToDto(image2));

        //when
        when(imageRepository.findByOwnerUsernameOrderByLastModified(user.getUsername())).thenReturn(imageList);

        //then
        assertThat(EntityToDto.imageEntityToDto(imageList)).isEqualTo(imageResponseDtoSet);
    }

    @Test
    public void shouldSearchImages() {
        //given
        Keyword keyword = new Keyword("sky");
        Set<Keyword> keywordSet = new HashSet<>();
        keywordSet.add(keyword);

        User user = new User("username","user@gmail.com","first name","last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image1 = new Image(5L,user,"my_path", Instant.now(),Instant.now(),"a picture of the sky",0,new Category(),0,0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());
        Image image2 = new Image(6L,user,"my_path", Instant.now(),Instant.now(),"description2",0,new Category(),0,0, ImageType.PNG, new HashSet<>(), new HashSet<>(), keywordSet);

        List<Image> imageListFromDescription = new ArrayList<>();
        imageListFromDescription.add(image1);

        List<Image> imageListFromKeyword = new ArrayList<>();
        imageListFromKeyword.add(image2);

        when(imageRepository.findByDescriptionContainingIgnoreCaseOrderByLastModified(keyword.getName())).thenReturn(imageListFromDescription);
        when(imageRepository.findByKeywordsContainingOrderByLastModified(keyword)).thenReturn(imageListFromKeyword);
    }
}