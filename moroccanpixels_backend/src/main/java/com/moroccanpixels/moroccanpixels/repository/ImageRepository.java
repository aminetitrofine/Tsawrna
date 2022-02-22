package com.moroccanpixels.moroccanpixels.repository;

import com.moroccanpixels.moroccanpixels.model.entity.Image;
import com.moroccanpixels.moroccanpixels.model.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findByOwnerUsername(String username);
    List<Image> findByDescriptionContaining(String q);
    List<Image> findByKeywordsContaining(Keyword keyword);
}