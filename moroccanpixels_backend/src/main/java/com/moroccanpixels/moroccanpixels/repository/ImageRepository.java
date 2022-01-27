package com.moroccanpixels.moroccanpixels.repository;

import com.moroccanpixels.moroccanpixels.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

}