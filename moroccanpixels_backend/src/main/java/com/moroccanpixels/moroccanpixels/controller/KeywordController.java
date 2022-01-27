package com.moroccanpixels.moroccanpixels.controller;

import com.moroccanpixels.moroccanpixels.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("keyword")
public class KeywordController {
    private final KeywordService keywordService;

    @Autowired
    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }


    @GetMapping
    public List<String> getKeywords() {
        return keywordService.getKeywords();
    }
}
