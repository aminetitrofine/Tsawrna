package com.moroccanpixels.moroccanpixels.keyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    @Autowired
    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public List<String> getKeywords() {
        return keywordRepository.findAll().stream().map(Keyword::getName).toList();
    }
}
