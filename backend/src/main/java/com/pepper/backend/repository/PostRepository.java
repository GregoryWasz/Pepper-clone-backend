package com.pepper.backend.repository;

import com.pepper.backend.dto.CreatePostDto;
import com.pepper.backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<CreatePostDto> findByPostId(long id);
}
