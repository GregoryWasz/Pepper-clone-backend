package com.pepper.backend.api;

import com.pepper.backend.dto.CreatePostDto;
import com.pepper.backend.dto.MessageDto;
import com.pepper.backend.dto.UpdatePostDto;
import com.pepper.backend.model.Post;
import com.pepper.backend.service.PostService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping("/posts")
    public ResponseEntity<?> addPost(@RequestBody CreatePostDto createPostDto) {
        return ResponseEntity.ok(postService.addPost(createPostDto));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody UpdatePostDto updatePostDto) {
        return ResponseEntity.ok(postService.updatePost(id, updatePostDto));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok(new MessageDto("Post successfully deleted"));
    }

    // TODO Search Get by name (search tags by PostName)
    // TODO Get all user posts -> Post
    // TODO VALIDATION
}
