package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.model.entity.Keyword;
import com.moroccanpixels.moroccanpixels.repository.KeywordRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

public class KeywordServiceTest {
    private final KeywordRepository keywordRepository = mock(KeywordRepository.class);

    @Test
    void shouldGetKeywords() {
        List<String> keywordsList = new ArrayList<>();
        keywordsList.add("sea");
        keywordsList.add("sky");
        keywordsList.add("blue");

        when(keywordRepository.findAll().stream().map(Keyword::getName).collect(Collectors.toList())).thenReturn(keywordsList);
    }
}