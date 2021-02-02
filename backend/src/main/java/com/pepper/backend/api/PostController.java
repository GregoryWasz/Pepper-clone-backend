package com.pepper.backend.api;

import com.pepper.backend.dto.CreatePostDto;
import com.pepper.backend.dto.UpdatePostDto;
import com.pepper.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


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

    @GetMapping("/search")
    public ResponseEntity<?> getPostByTitle(@RequestParam String q) {
        return ResponseEntity.ok(postService.getPostByTitle(q));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody UpdatePostDto updatePostDto, Principal principal) {
        return ResponseEntity.ok(postService.updatePost(id, updatePostDto, principal));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> deletePost(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(postService.deletePost(id, principal));
    }

    @GetMapping("/page/")
    public ResponseEntity<?> postPage(@RequestParam(defaultValue = "0", required = false) int page,
                                      @RequestParam(defaultValue = "5", required = false) int size) {
        return ResponseEntity.ok(postService.PostPages(PageRequest.of(page,size, Sort.by("postDate").descending() )));
    }
}
