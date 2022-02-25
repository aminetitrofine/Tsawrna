package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.auth.AuthenticationFacade;
import com.moroccanpixels.moroccanpixels.config.ImageConfig;
import com.moroccanpixels.moroccanpixels.dto.ImageRequestDto;
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
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

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
    @Disabled
    void shouldUploadImage() {
        /*File file = new File("pexels-min-an-713664.jpg");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("image-sky",
                "pexels-min-an-713664.jpg", "image/jpeg", IOUtils.toByteArray(input));*/
        //ImageService imageService = new ImageService(imageRepository,userRepository,keywordRepository,request,authenticationFacade,imageConfig);

        /*Assertions.assertThatThrownBy(() -> {
            imageService.uploadImage(new ImageRequestDto("C:\\Users\\User\\Downloads\\assali_affiche.jpg","affiche assali eitc"));
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("exception msg");*/
    }

    @Test
    void shouldListImages() {
        //given
        User user = new User("username","user@gmail.com","first name","last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image1 = new Image(5L,user,"my_path", Instant.now(),Instant.now(),"description1",0, new Category(), 0, 0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());
        Image image2 = new Image(6L,user,"my_path", Instant.now(),Instant.now(),"description2",0, new Category(), 0, 0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());

        List<Image> imageList = new ArrayList<Image>();
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
    @Disabled
    void shouldViewImage() throws IOException {
        User user = new User("username","user@gmail.com","first name","last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image = new Image(5L,user,"my_path", Instant.now(),Instant.now(),"description",0,new Category(),0,0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());

        when(imageRepository.findById(5L)).thenReturn(Optional.of(image));

        String directory = "my_directory";
        when(imageConfig.getDirectory()).thenReturn(directory);
        InputStream in = new FileInputStream(directory + image.getLocalPath());

        byte[] convertedImage = IOUtils.toByteArray(in);

        assertThat(IOUtils.toByteArray(in)).isEqualTo(convertedImage);
        //to be continued..
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
    @Disabled
    void shouldUpdateImage() {
        User user = new User("username","user@gmail.com","first name","last name","secret", ApplicationUserRole.CONTRIBUTOR, StatusType.CONFIRMED);
        Image image = new Image(1L,user,"image_path", Instant.now(),Instant.now(),"description",0,new Category(),0,0, ImageType.PNG, new HashSet<>(), new HashSet<>(), new HashSet<>());
        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));
        String file1Name = image.getId() + "." + image.getType().value();

        //verifying ownership
        String username = "username";
        when(authenticationFacade.getAuthenticatedUsername()).thenReturn(username);
        assertThat(username).isEqualTo(image.getOwner().getUsername());

        //updating description
        /*ImageRequestDto imageRequestDto = new ImageRequestDto(null,"description");
        assertThat(imageRequestDto.getDescription()).isNotNull();
        image.setDescription(imageRequestDto.getDescription());

        //updating file
        MultipartFile file = imageRequestDto.getFile();
        ImageResponseDto imageResponseDto = EntityToDto.imageEntityToDto(image);
        assertThat(file).isNull();
        assertThat(EntityToDto.imageEntityToDto(image)).isEqualTo(imageResponseDto);

        //updating image type
        image.setType(ImageType.fromContentType(file.getContentType()));*/

        //to be continued...

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
        //Mockito.verify(user,Mockito.times(1)).addSavedImage(ArgumentMatchers.any(Image.class));

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

        List<Image> imageList = new ArrayList<Image>();
        imageList.add(image1); imageList.add(image2);

        Set<ImageResponseDto> imageResponseDtoSet = new HashSet<>();
        imageResponseDtoSet.add(EntityToDto.imageEntityToDto(image1));
        imageResponseDtoSet.add(EntityToDto.imageEntityToDto(image2));

        //when
        when(imageRepository.findByOwnerUsername(user.getUsername())).thenReturn(imageList);

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

        List<Image> imageListFromDescription = new ArrayList<Image>();
        imageListFromDescription.add(image1);

        List<Image> imageListFromKeyword = new ArrayList<Image>();
        imageListFromKeyword.add(image2);

        /*Set<ImageResponseDto> imageResponseDtoSet = new HashSet<>();
        imageResponseDtoSet.add(EntityToDto.imageEntityToDto(image1));
        imageResponseDtoSet.add(EntityToDto.imageEntityToDto(image2));*/

        when(imageRepository.findByDescriptionContainingIgnoreCase(keyword.getName())).thenReturn(imageListFromDescription);
        when(imageRepository.findByKeywordsContaining(keyword)).thenReturn(imageListFromKeyword);
    }
}