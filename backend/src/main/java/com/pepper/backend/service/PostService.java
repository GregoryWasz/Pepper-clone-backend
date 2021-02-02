package com.pepper.backend.service;

import com.pepper.backend.dto.*;
import com.pepper.backend.exception.ResourceNotFoundException;
import com.pepper.backend.model.Post;
import com.pepper.backend.model.User;
import com.pepper.backend.repository.CommentRepository;
import com.pepper.backend.repository.PostRepository;
import com.pepper.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    public List<PostDto> getAllPosts(){
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> ( new PostDto(
                        post.getPostId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getPostDate(),
                        post.getPriceBefore(),
                        post.getPriceAfter(),
                        post.getVotes(),
                        post.getDealLink(),
                        post.getTagId(),
                        userRepository.findByUserId(post.getUserId()).getUsername()))
                )
                .collect(Collectors.toList());
    }

    public Post addPost(CreatePostDto CreatePostDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = authentication.getName();
        User user = userRepository.findByUsername(loggedInUserName)
                .orElseThrow(() ->new ResourceNotFoundException("User not found"));

        Post post = new Post();
        post.setUserId(user.getUserId());
        post.setTitle(CreatePostDto.getTitle());
        post.setContent(CreatePostDto.getContent());
        post.setPriceBefore(CreatePostDto.getPriceBefore());
        post.setPriceAfter(CreatePostDto.getPriceAfter());
        post.setDealLink(CreatePostDto.getDealLink());
        post.setTagId(CreatePostDto.getTagId());
        return postRepository.save(post);
    }

    public Post getPostById(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not exist"));
    }

    public ResponseEntity<?> deletePost(long id, Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not exist"));

        Post databasePost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not exist"));

        if (user.getUserId() != databasePost.getUserId()){
            return ResponseEntity.ok(new MessageDto("You don't have permission to do it"));
        }

        commentRepository.deleteAllByPostId(id);
        postRepository.delete(databasePost);
        return ResponseEntity.ok(new MessageDto("Post Successfully Deleted"));
    }

    public ResponseEntity<?> updatePost(long id, UpdatePostDto userPost, Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not exist"));

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not exist"));

        if (user.getUserId() != post.getUserId()){
            return ResponseEntity.ok(new MessageDto("You don't have permission to do it"));
        }

        post.setTitle(userPost.getTitle());
        post.setContent(userPost.getContent());
        post.setPriceBefore(userPost.getPriceBefore());
        post.setPriceAfter(userPost.getPriceAfter());
        post.setActive(userPost.getActive());
        postRepository.save(post);
        return ResponseEntity.ok(new MessageDto("Post successfully updated"));
    }

    public List<Post> getPostByTitle(String title) {
        return postRepository.findByTitleContains(title)
                .orElseThrow(() -> new ResourceNotFoundException("Post with title " + title + " not exist"));
    }

    public Page<PostDto> PostPages(Pageable pageable) {
        Page<Post> pagedPosts = postRepository.findAll(pageable);
        long totalElements = pagedPosts.getTotalElements();
        return new PageImpl<>(pagedPosts
                .stream()
                .map(post -> ( new PostDto(
                        post.getPostId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getPostDate(),
                        post.getPriceBefore(),
                        post.getPriceAfter(),
                        post.getVotes(),
                        post.getDealLink(),
                        post.getTagId(),
                        userRepository.findByUserId(post.getUserId()).getUsername()))
                )
                .collect(Collectors.toList()), pageable, totalElements);
    }
}
