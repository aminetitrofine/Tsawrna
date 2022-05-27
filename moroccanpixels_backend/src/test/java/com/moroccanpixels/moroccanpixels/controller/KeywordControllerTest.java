package com.moroccanpixels.moroccanpixels.controller;

import com.moroccanpixels.moroccanpixels.service.KeywordService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class KeywordControllerTest {
    private final KeywordService keywordService = Mockito.mock(KeywordService.class);

    @Test
    void shouldGetKeywords() {
        List<String> keywordsList = new ArrayList<>();
        keywordsList.add("sea");
        keywordsList.add("sky");
        keywordsList.add("blue");

        when(keywordService.getKeywords()).thenReturn(keywordsList);

        KeywordController keywordController= new KeywordController(keywordService);
        assertEquals(keywordsList, keywordController.getKeywords());
    }
}