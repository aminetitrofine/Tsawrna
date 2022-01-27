package com.moroccanpixels.moroccanpixels.repository;

import com.moroccanpixels.moroccanpixels.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword,Long> {
    Optional<Keyword> findByName(String name);
}