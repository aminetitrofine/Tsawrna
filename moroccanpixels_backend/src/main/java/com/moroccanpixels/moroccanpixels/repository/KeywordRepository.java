package com.moroccanpixels.moroccanpixels.repository;

import com.moroccanpixels.moroccanpixels.model.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword,Long> {
    Optional<Keyword> findByName(String name);
    List<Keyword> findByNameContains(String name);
}
