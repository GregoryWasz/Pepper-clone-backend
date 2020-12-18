package com.pepper.backend.api;

import com.pepper.backend.dto.CreatePostDto;
import com.pepper.backend.dto.MessageDto;
import com.pepper.backend.dto.UpdatePostDto;
import com.pepper.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> addPost(@RequestBody CreatePostDto createPostDto) {
        return ResponseEntity.ok(postService.addPost(createPostDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<?> getPostByTitle(@PathVariable String title) {
        return ResponseEntity.ok(postService.getPostByTitle(title));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody UpdatePostDto updatePostDto) {
        return ResponseEntity.ok(postService.updatePost(id, updatePostDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok(new MessageDto("Post successfully deleted"));
    }
    // TODO PAGINATION
    // TODO VALIDATION
}
