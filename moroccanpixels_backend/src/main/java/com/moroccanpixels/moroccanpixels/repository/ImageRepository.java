package com.moroccanpixels.moroccanpixels.repository;

import com.moroccanpixels.moroccanpixels.model.entity.Image;
import com.moroccanpixels.moroccanpixels.model.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findByOwnerUsernameOrderByLastModifiedDesc(String username);
    List<Image> findByDescriptionContainingIgnoreCaseOrderByLastModifiedDesc(String q);
    List<Image> findByKeywordsContainingOrderByLastModifiedDesc(Keyword keyword);
}