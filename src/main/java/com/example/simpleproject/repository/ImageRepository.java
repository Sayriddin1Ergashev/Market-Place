package com.example.simpleproject.repository;

import com.example.simpleproject.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    Optional<Image> findByImageIdAndDeletedAtIsNull(Integer userId);

    boolean existsByPath(String path);

    boolean existsByType(String type);

    boolean existsBySize(Integer size);

    boolean existsByToken(String token);
}
